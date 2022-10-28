package com.hong.assignment.ui.model

import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker


data class MapUiModel(
    val marker: Marker,
    val info: InfoWindow,
    val message : String
)