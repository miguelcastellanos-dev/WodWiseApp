package com.migueldev.wodwiseapp.di

import com.google.firebase.auth.FirebaseAuth
import com.migueldev.wodwiseapp.data.remote.datasource.login.LoginDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.login.RemoteLoginDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.signup.RemoteSignUpDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.signup.SignUpDatasource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginModule {
    @Binds
    abstract fun bindLoginDatasource(
        remoteLoginDatasource: RemoteLoginDatasource,
    ): LoginDatasource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpModule {
    @Binds
    abstract fun bindSignUpDatasource(
        remoteSignupDatasource: RemoteSignUpDatasource,
    ): SignUpDatasource
}
