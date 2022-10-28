package com.hong.assignment.ui.bindingadapter

import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("progress")
    fun setLinearProgress(
        indicator: LinearProgressIndicator,
        progress: Int
    ) {
        indicator.setProgressCompat(progress, true)
    }
}