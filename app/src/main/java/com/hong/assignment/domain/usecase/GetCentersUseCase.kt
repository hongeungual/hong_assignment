package com.hong.assignment.domain.usecase

import com.hong.assignment.domain.repository.CentersRepository
import com.hong.assignment.model.query.CentersQueryMap
import com.hong.assignment.model.ui.CentersUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class GetCentersUseCase @Inject constructor(
    private val repository: CentersRepository
) {
    operator fun invoke(queryMap: CentersQueryMap): Single<CentersUiModel> {
        return repository.fetch(queryMap)
            .map { response ->
                // 데이터가 없는 경우 에러 리턴
                if (response.list.isEmpty()) {
                    throw NullPointerException("List is Empty..")
                }
                return@map CentersUiModel(
                    queryMap.pageNo,
                    response.list
                )
            }
    }
}
