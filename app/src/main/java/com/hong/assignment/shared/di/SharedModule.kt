package com.hong.assignment.shared.di

import com.hong.assignment.shared.LocationManager
import com.hong.assignment.shared.ResourceManager
import com.hong.assignment.shared.impl.LocationManagerImpl
import com.hong.assignment.shared.impl.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
internal abstract class SharedModule {
    @Singleton
    @Binds
    abstract fun bindResourceManager(manager: ResourceManagerImpl): ResourceManager

    @Singleton
    @Binds
    abstract fun bindLocationManager(manager: LocationManagerImpl): LocationManager
}
