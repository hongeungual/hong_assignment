package com.hong.assignment.model.enums


enum class CenterType(val txt: String) {
    REGION("지역"),
    CENTER("중앙/권역");

    companion object {
        fun from(str: String): CenterType {
            return values().firstOrNull { it.txt == str } ?: REGION
        }
    }
}
