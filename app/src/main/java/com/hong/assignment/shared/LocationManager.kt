package com.hong.assignment.shared

import android.location.Location


interface LocationManager {
    fun getLastLocation(): Location?
}
