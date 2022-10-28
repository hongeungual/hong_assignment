package com.hong.assignment.data.remote

import com.hong.assignment.model.entity.CentersResponseEntity
import com.hong.assignment.model.query.CentersQueryMap
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface CentersApiService {

    @GET("/api/15077586/v1/centers")
    fun fetch(@QueryMap(encoded = true) queryMap: CentersQueryMap): Single<CentersResponseEntity>
}
