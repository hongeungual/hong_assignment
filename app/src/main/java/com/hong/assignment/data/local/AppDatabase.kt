package com.hong.assignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hong.assignment.data.local.dao.CentersDao
import com.hong.assignment.data.local.model.CentersLocalEntity


@Database(
    entities = [CentersLocalEntity::class],
    version = 1
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun centersDao(): CentersDao
}
