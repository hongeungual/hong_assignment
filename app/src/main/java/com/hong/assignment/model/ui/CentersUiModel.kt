package com.hong.assignment.model.ui

import com.hong.assignment.model.entity.CentersEntity

data class CentersUiModel(
    val currentPage: Int,
    val list: List<CentersEntity> = listOf()
)
