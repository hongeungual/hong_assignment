package com.hong.assignment.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CentersResponseEntity(
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
    val currentCount: Int,
    val matchCount: Int,
    @SerialName("data")
    val list: List<CentersEntity> = listOf()
)