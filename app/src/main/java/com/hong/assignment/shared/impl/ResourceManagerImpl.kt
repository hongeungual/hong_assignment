package com.hong.assignment.shared.impl

import android.content.Context
import androidx.core.content.ContextCompat
import com.hong.assignment.shared.ResourceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2022/10/20
 */
internal class ResourceManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {
    override fun getColor(color: Int) = ContextCompat.getColor(context, color)

    override fun getString(resId: Int) = context.getString(resId)
}
