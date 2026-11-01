package com.biharride.app.data.repository

import com.biharride.app.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID
import kotlin.random.Random

class RideRepository {
    private val _currentRide = MutableStateFlow<Ride?>(null)

    fun calculateFare(vehicleType: VehicleType, distanceKm: Double, durationMins: Int): Double {
        val base = when (vehicleType) {
            VehicleType.BIKE -> 20.0
            VehicleType.E_RICKSHAW -> 25.0
            VehicleType.AUTO -> 35.0
            VehicleType.CAR -> 60.0
        }
        val perKm = when (vehicleType) {
            VehicleType.BIKE -> 7.0
            VehicleType.E_RICKSHAW -> 8.0
            VehicleType.AUTO -> 12.0
            VehicleType.CAR -> 16.0
        }
        return (base + (distanceKm * perKm) + (durationMins * 1.5)).coerceAtLeast(30.0)
    }

    fun verifyOtpAndStartTrip(enteredOtp: String): Boolean {
        val ride = _currentRide.value ?: return false
        if (ride.otp == enteredOtp.trim()) {
            _currentRide.value = ride.copy(status = RideStatus.RIDE_STARTED)
            return true
        }
        return false
    }
}