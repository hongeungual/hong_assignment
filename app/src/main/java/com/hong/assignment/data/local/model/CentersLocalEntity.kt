package com.hong.assignment.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hong.assignment.data.local.model.CentersLocalEntity.Companion.TABLE_NAME
import com.hong.assignment.model.enums.CenterType


@Entity(tableName = TABLE_NAME)
data class CentersLocalEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = Column.ID) // 예방 접종 센터 고유 식별자
    val id: Int,
    @ColumnInfo(name = Column.NAME) // 예방 접종 센터 명
    val name: String,
    @ColumnInfo(name = Column.SIDO) // 시도
    val sido: String,
    @ColumnInfo(name = Column.SIGUNGU) // 시군구
    val sigungu: String,
    @ColumnInfo(name = Column.FACILITY_NAME) // 시설명
    val facilityName: String,
    @ColumnInfo(name = Column.ZIPCODE) // 우편 번호
    val zipCode: String,
    @ColumnInfo(name = Column.ADDRESS) // 주소
    val address: String,
    @ColumnInfo(name = Column.LAT) // 좌표(위도)
    val lat: String,
    @ColumnInfo(name = Column.LNG) // 좌표 (경도)
    val lng: String,
    @ColumnInfo(name = Column.CREATED_AT)
    val createdAt: String,
    @ColumnInfo(name = Column.UPDATED_AT)
    val updatedAt: String,
    @ColumnInfo(name = Column.TYPE) // 예방 접종 센터 유형
    val type: String,
    @ColumnInfo(name = Column.ORG) // 운영기관
    val org: String,
    @ColumnInfo(name = Column.PHONE_NUM) // 사무실 전화번호
    val phoneNum: String
) {

    var centerType: CenterType? = null
        get() {
            if (field == null) {
                field = CenterType.from(type)
            }
            return field
        }

    companion object {
        const val TABLE_NAME = "centers"

        object Column {
            const val ID = "id"
            const val NAME = "center_name"
            const val SIDO = "sido"
            const val SIGUNGU = "sigungu"
            const val FACILITY_NAME = "facilityName"
            const val ZIPCODE = "zipcode"
            const val ADDRESS = "address"
            const val LAT = "latitude"
            const val LNG = "longitude"
            const val CREATED_AT = "created_at"
            const val UPDATED_AT = "updated_at"
            const val TYPE = "center_type"
            const val ORG = "org"
            const val PHONE_NUM = "phone_number"
        }
    }
}