package com.hong.assignment.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CentersEntity(
    @SerialName("id") // 예방 접종 센터 고유 식별자
    val id: Int,
    @SerialName("centerName") // 예방 접종 센터 명
    val name: String,
    @SerialName("sido") // 시도
    val sido: String,
    @SerialName("sigungu") // 시군구
    val sigungu: String,
    @SerialName("facilityName") // 시설명
    val facilityName: String,
    @SerialName("zipCode") // 우편 번호
    val zipCode: String,
    @SerialName("address") // 주소
    val address: String,
    @SerialName("lat") // 좌표(위도)
    val lat: String,
    @SerialName("lng") // 좌표 (경도)
    val lng: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("updatedAt")
    val updatedAt: String,
    // 예방 접종 센터 유형 '지역', '중앙/권역' 아무리 찾아봐도 해당 CenterType 에 대한 EnumClass 같은 정보가 없어서
    // https://www.data.go.kr/tcs/dss/selectFileDataDetailView.do?publicDataPk=15077603#layer_data_infomation 직접 json 파일 받아서 찾아봄
    @SerialName("centerType")
    val centerType: String,
    @SerialName("org") // 운영기관
    val org: String,
    @SerialName("phoneNumber") // 사무실 전화번호
    val phoneNum: String
)