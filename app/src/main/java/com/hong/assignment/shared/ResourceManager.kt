package com.hong.assignment.shared

import androidx.annotation.ColorRes
import androidx.annotation.StringRes


interface ResourceManager {
    fun getColor(@ColorRes color: Int): Int
    fun getString(@StringRes resId: Int): String
}