package com.hong.assignment.domain.repository

import com.hong.assignment.data.local.model.CentersLocalEntity
import com.hong.assignment.model.entity.CentersResponseEntity
import com.hong.assignment.model.query.CentersQueryMap
import io.reactivex.rxjava3.core.Single


interface CentersRepository {
    fun fetch(queryMap: CentersQueryMap): Single<CentersResponseEntity>
    fun insertAll(list: List<CentersLocalEntity>): Single<List<Long>>
    fun deleteAll(): Single<Int>
    fun findAll(): Single<List<CentersLocalEntity>>
}
