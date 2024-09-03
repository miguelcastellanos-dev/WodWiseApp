package com.migueldev.wodwiseapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.migueldev.wodwiseapp.data.remote.datasource.login.LoginDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.login.RemoteLoginDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.signup.RemoteSignUpDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.signup.SignUpDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.weight.RemoteWeightDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.weight.WeightsDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.workout.RemoteWorkoutDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.workout.WorkoutDatasource
import com.migueldev.wodwiseapp.data.remote.service.AuthService
import com.migueldev.wodwiseapp.data.session.UserPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideLoginDatasource(
        authService: AuthService,
    ): LoginDatasource = RemoteLoginDatasource(authService)

    @Provides
    @Singleton
    fun provideSignUpDatasource(
        authService: AuthService,
    ): SignUpDatasource = RemoteSignUpDatasource(authService)

    @Singleton
    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideRemoteWorkoutDatasource(
        firebaseFirestore: FirebaseFirestore,
        userPreferences: UserPreferences,
    ): WorkoutDatasource = RemoteWorkoutDatasource(
        firebaseFirestore,
        userPreferences
    )

    @Provides
    @Singleton
    fun provideRemoteWeightDatasource(
        firebaseFirestore: FirebaseFirestore,
        userPreferences: UserPreferences,
    ): WeightsDatasource {
        return RemoteWeightDatasource(
            firebaseFirestore,
            userPreferences
        )
    }
}
