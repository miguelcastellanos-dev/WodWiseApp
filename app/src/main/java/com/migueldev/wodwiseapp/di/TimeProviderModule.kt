package com.migueldev.wodwiseapp.di

import com.migueldev.wodwiseapp.domain.providers.DefaultTimeProvider
import com.migueldev.wodwiseapp.domain.providers.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TimeProviderModule {

    @Provides
    fun provideTimeProvider(): TimeProvider {
        return DefaultTimeProvider()
    }
}
