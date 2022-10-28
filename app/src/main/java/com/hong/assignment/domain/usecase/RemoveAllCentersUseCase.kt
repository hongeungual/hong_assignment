package com.hong.assignment.domain.usecase

import com.hong.assignment.domain.repository.CentersRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class RemoveAllCentersUseCase @Inject constructor(
    private val repository: CentersRepository
) {
    operator fun invoke(): Single<Int> {
        return repository.deleteAll()
    }
}
