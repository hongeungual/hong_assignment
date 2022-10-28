package com.hong.assignment.domain.usecase

import com.hong.assignment.data.local.model.CentersLocalEntity
import com.hong.assignment.domain.repository.CentersRepository
import com.hong.assignment.model.entity.CentersEntity
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class SaveCentersUseCase @Inject constructor(
    private val repository: CentersRepository
) {
    operator fun invoke(list: List<CentersEntity>): Single<List<Long>> {
        return Single.just(list.map { it.toMap() })
            .flatMap { repository.insertAll(it) }
    }

    private fun CentersEntity.toMap(): CentersLocalEntity {
        return CentersLocalEntity(
            id,
            name,
            sido,
            sigungu,
            facilityName,
            zipCode,
            address,
            lat,
            lng,
            createdAt,
            updatedAt,
            centerType,
            org,
            phoneNum
        )
    }
}