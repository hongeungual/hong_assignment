package com.hong.assignment.shared.impl

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.hong.assignment.shared.LocationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


internal class LocationManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationManager {
    override fun getLastLocation(): Location? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val lm =
                context.getSystemService(Context.LOCATION_SERVICE) as android.location.LocationManager
            val gpsLocation = lm.getLastKnownLocation(android.location.LocationManager.GPS_PROVIDER)
            val gpsAccuracy = gpsLocation?.accuracy ?: -1F
            val networkLocation =
                lm.getLastKnownLocation(android.location.LocationManager.NETWORK_PROVIDER)
            val networkAccuracy = networkLocation?.accuracy ?: -1F
            if (gpsAccuracy > networkAccuracy) {
                gpsLocation
            } else {
                networkLocation
            }
        } else {
            null
        }
    }
}
