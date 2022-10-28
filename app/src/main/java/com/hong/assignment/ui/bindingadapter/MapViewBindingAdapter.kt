package com.hong.assignment.ui.bindingadapter

import android.location.Location
import androidx.databinding.BindingAdapter
import com.hong.assignment.ui.model.MapUiModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.overlay.InfoWindow


object MapViewBindingAdapter {

    interface MarkerClickListener {
        fun onMarkerClick(uiModel: MapUiModel)
    }

    /**
     * 네이버 지도에 마커 표시 및 마커 클릭 이벤트 처리
     */
    @JvmStatic
    @BindingAdapter(
        value = [
            "markerList",
            "onMarkerClick"
        ], requireAll = false
    )
    fun setMapViewMarkerList(
        mapView: MapView,
        uiList: List<MapUiModel>?,
        markerClickListener: MarkerClickListener?
    ) {
        if (uiList.isNullOrEmpty()) return

        mapView.getMapAsync { map ->
            uiList.forEach { uiModel ->
                uiModel.marker.map = map
                uiModel.marker.setOnClickListener {
                    markerClickListener?.onMarkerClick(uiModel)
                    true
                }
                uiModel.info.adapter = object : InfoWindow.DefaultTextAdapter(mapView.context) {
                    override fun getText(p0: InfoWindow): CharSequence {
                        return uiModel.message
                    }
                }
            }
        }
    }

    /**
     * 마커 클릭시 카메라 이동 처리
     */
    @JvmStatic
    @BindingAdapter("currentMarker")
    fun setCurrentMarkerMoveCamera(
        mapView: MapView,
        currentMarker: MapUiModel?
    ) {
        if (currentMarker == null) return

        val cameraUpdate = CameraUpdate.scrollTo(currentMarker.marker.position)
            .animate(CameraAnimation.Easing)
        mapView.getMapAsync { it.moveCamera(cameraUpdate) }
    }

    /**
     * 현재 위치 아이콘 클릭시 GPS 상 현재 위치로 이동처리하는 함수
     */
    @JvmStatic
    @BindingAdapter("currentLocation")
    fun setCurrentLocation(
        mapView: MapView,
        location: Location?
    ) {
        if (location == null) return

        val cameraUpdate = CameraUpdate.scrollTo(LatLng(location))
            .animate(CameraAnimation.Fly)
        mapView.getMapAsync {
            it.locationOverlay.isVisible = true
            it.locationOverlay.position = LatLng(location)
            it.moveCamera(cameraUpdate)
        }
    }
}
