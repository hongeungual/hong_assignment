package com.hong.assignment.domain.usecase

import com.hong.assignment.data.local.model.CentersLocalEntity
import com.hong.assignment.domain.repository.CentersRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class GetLocalCentersUseCase @Inject constructor(
    private val repository: CentersRepository
) {
    operator fun invoke(): Single<List<CentersLocalEntity>> {
        return repository.findAll()
    }
}