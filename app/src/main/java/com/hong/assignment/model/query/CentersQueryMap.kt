package com.hong.assignment.model.query

import com.hong.assignment.BuildConfig


class CentersQueryMap : HashMap<String, Any>() {

    var pageNo: Int = 1
        set(value) {
            put("page", value)
            field = value
        }
    var pageSize: Int = 10
        set(value) {
            put("perPage", value)
            field = value
        }

    var returnType: String = "JSON"
        set(value) {
            put("returnType", value)
            field = value
        }

    init {
        pageNo = 1
        pageSize = 10
        // Service Key Define
        put("serviceKey", BuildConfig.SERVICE_KEY)
    }
}