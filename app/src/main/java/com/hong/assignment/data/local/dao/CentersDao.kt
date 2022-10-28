package com.hong.assignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hong.assignment.data.local.model.CentersLocalEntity
import io.reactivex.rxjava3.core.Single


@Dao
internal abstract class CentersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<CentersLocalEntity>): Single<List<Long>>

    @Query("DELETE FROM ${CentersLocalEntity.TABLE_NAME}")
    abstract fun deleteAll(): Single<Int>

    @Query("SELECT * FROM ${CentersLocalEntity.TABLE_NAME}")
    abstract fun findAll(): Single<List<CentersLocalEntity>>
}