package com.biharride.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserRole { CUSTOMER, DRIVER, ADMIN }

enum class VehicleType(val displayName: String) {
    BIKE("Bike"),
    AUTO("Auto Rickshaw"),
    E_RICKSHAW("E-Rickshaw"),
    CAR("Car")
}

enum class RideStatus {
    SEARCHING,
    DRIVER_ASSIGNED,
    DRIVER_ARRIVING,
    DRIVER_ARRIVED,
    RIDE_STARTED,
    RIDE_COMPLETED,
    CANCELLED
}

@Entity(tableName = "cached_rides")
data class Ride(
    @PrimaryKey val rideId: String,
    val customerId: String,
    val customerName: String,
    val driverId: String?,
    val driverName: String?,
    val vehicleType: VehicleType,
    val vehicleNumber: String?,
    val pickupAddress: String,
    val destinationAddress: String,
    val distanceKm: Double,
    val estimatedFare: Double,
    val finalFare: Double,
    val otp: String,
    val status: RideStatus,
    val createdAt: Long = System.currentTimeMillis()
)