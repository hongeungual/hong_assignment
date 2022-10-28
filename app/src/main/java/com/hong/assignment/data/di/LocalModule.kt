package com.hong.assignment.data.di

import android.content.Context
import androidx.room.Room
import com.hong.assignment.data.local.AppDatabase
import com.hong.assignment.data.local.dao.CentersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
internal object LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "room")
        .setQueryExecutor(Executors.newCachedThreadPool())
        .setTransactionExecutor(Executors.newCachedThreadPool())
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCentersDao(db: AppDatabase): CentersDao = db.centersDao()
}
