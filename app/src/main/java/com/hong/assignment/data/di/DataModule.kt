package com.hong.assignment.data.di

import com.hong.assignment.data.remote.impl.CentersRepositoryImpl
import com.hong.assignment.domain.repository.CentersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module(includes = [RemoteModule::class, LocalModule::class])
internal abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindCentersRepository(repository: CentersRepositoryImpl): CentersRepository
}
