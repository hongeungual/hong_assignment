package com.hong.assignment.data.remote.impl

import com.hong.assignment.data.local.dao.CentersDao
import com.hong.assignment.data.local.model.CentersLocalEntity
import com.hong.assignment.data.remote.CentersApiService
import com.hong.assignment.domain.repository.CentersRepository
import com.hong.assignment.model.entity.CentersResponseEntity
import com.hong.assignment.model.query.CentersQueryMap
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

internal class CentersRepositoryImpl @Inject constructor(
    private val localDao: CentersDao,
    private val apiService: CentersApiService
) : CentersRepository {
    override fun fetch(queryMap: CentersQueryMap): Single<CentersResponseEntity> {
        return apiService.fetch(queryMap)
    }

    override fun insertAll(list: List<CentersLocalEntity>): Single<List<Long>> {
        return localDao.insert(list)
    }

    override fun deleteAll(): Single<Int> {
        return localDao.deleteAll()
    }

    override fun findAll(): Single<List<CentersLocalEntity>> {
        return localDao.findAll()
    }
}