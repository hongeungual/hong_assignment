package com.hong.assignment.ui.map

import android.graphics.Color
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.assignment.data.local.model.CentersLocalEntity
import com.hong.assignment.domain.usecase.GetLocalCentersUseCase
import com.hong.assignment.model.enums.CenterType
import com.hong.assignment.shared.LocationManager
import com.hong.assignment.ui.model.MapUiModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.MarkerIcons
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationManager: LocationManager,
    private val getLocalCentersUseCase: GetLocalCentersUseCase
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }

    private val _markerList: MutableLiveData<MutableList<MapUiModel>> by lazy { MutableLiveData() }
    val markerList: LiveData<MutableList<MapUiModel>> get() = _markerList

    private val _currentMarker: MutableLiveData<MapUiModel> by lazy { MutableLiveData() }
    val currentMarker: LiveData<MapUiModel> get() = _currentMarker

    private val _currentLocation: MutableLiveData<Location> by lazy { MutableLiveData() }
    val currentLocation: LiveData<Location> get() = _currentLocation

    fun start() {
        getLocalCentersUseCase()
            .map { toMarkerList(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Timber.d("Marker Size ${it.size}")
                _markerList.value = it.toMutableList()
            }, {
                Timber.d("ERROR $it")
            }).addTo(compositeDisposable)
    }


    private fun toMarkerList(list: List<CentersLocalEntity>): List<MapUiModel> {
        Timber.d("Local Size ${list.size}")
        val markerList = mutableListOf<MapUiModel>()
        // https://navermaps.github.io/android-map-sdk/guide-ko/5-2.html
        list.forEach {
            markerList.add(
                MapUiModel(
                    toMarker(it),
                    toInfo(it),
                    toMarkerTag(it)
                )
            )
        }
        return markerList
    }

    private fun toMarker(entity: CentersLocalEntity): Marker {
        val marker = Marker()
        marker.position = LatLng(entity.lat.toDouble(), entity.lng.toDouble())
        // marker.captionText = entity.name
        val iconPair = toMarkerIcon(entity.centerType)
        marker.icon = iconPair.second
        marker.iconTintColor = iconPair.first
        return marker
    }

    private fun toInfo(entity: CentersLocalEntity): InfoWindow {
        val info = InfoWindow()
        info.position = LatLng(entity.lat.toDouble(), entity.lng.toDouble())
        return info
    }


    private fun toMarkerIcon(type: CenterType?): Pair<Int, OverlayImage> {
        return if (type == CenterType.CENTER) {
            Color.RED to MarkerIcons.BLACK
        } else {
            Color.GREEN to MarkerIcons.GREEN
        }
    }

    private fun toMarkerTag(entity: CentersLocalEntity): String {
        val str = StringBuilder()
        str.append("[${entity.name}] ${entity.facilityName}")
        str.append("\n")
        str.append("주소. ${entity.address}")
        str.append("\n")
        str.append("No. ${entity.phoneNum}")
        str.append("Date. ${entity.updatedAt}")
        return str.toString()
    }


    fun onMarkerClick(uiModel: MapUiModel) {
        // 같은거 클릭시 해제
        if (currentMarker.value == uiModel) {
            uiModel.info.close()
            _currentMarker.value = null
        } else {
            // 이전에 선택한 마커 종료
            currentMarker.value?.info?.close()
            // 요구조건: 선택된 마커의 정보(Response Data)를 표시
            uiModel.info.open(uiModel.marker)
            _currentMarker.value = uiModel
        }
    }


    fun onCurrentLocation() {
        _currentLocation.value = locationManager.getLastLocation()
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}
