package com.hong.assignment.ui.map

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hmju.permissions.SimplePermissions
import com.hong.assignment.BR
import com.hong.assignment.R
import com.hong.assignment.databinding.ActivityMapBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MapActivity : AppCompatActivity() {

    private val viewModel: MapViewModel by viewModels()

    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performBinding()
        initNaverMap(savedInstanceState)

        // 지도 권한 체크
        SimplePermissions(this)
            .requestPermissions(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .build { b, strings ->
                Timber.d("isGranted $b ${strings.contentToString()}")
                if (b) {
                    viewModel.start()
                } else {
                    showPermissionsAlert()
                }
            }
    }

    private fun initNaverMap(bundle: Bundle?) {
        binding.mapView.onCreate(bundle)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    private fun showPermissionsAlert() {
        AlertDialog.Builder(this)
            .setMessage(R.string.txt_map_permissions_negative)
            .setCancelable(false)
            .setPositiveButton(R.string.txt_confirm) { _, _ ->
                finish()
            }
            .show()
    }


    private fun performBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vm, viewModel)
    }
}