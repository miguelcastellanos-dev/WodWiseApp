package com.migueldev.wodwiseapp.di

import android.app.Application
import android.content.Context
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.presentation.AppStateManager
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@IO ioDispatcher: CoroutineDispatcher, context: Context):
        UserPreferences {
        return UserPreferences(ioDispatcher, context)
    }

    @Provides
    @Singleton
    fun provideAppStateManager(
        userPreferences: UserPreferences,
        resourceProvider: ResourceProvider,
        toastWrapper: ToastWrapper,
    ): AppStateManager {
        return AppStateManager(
            userPreferences = userPreferences,
            resourceProvider = resourceProvider,
            toastWrapper = toastWrapper
        )
    }
}
