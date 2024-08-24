package com.migueldev.wodwiseapp.di

import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import com.migueldev.wodwiseapp.ApiKeyRetriever
import com.migueldev.wodwiseapp.data.remote.datasource.openai.OpenAIDatasource
import com.migueldev.wodwiseapp.data.remote.datasource.openai.RemoteOpenAIDatasource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OpenAIModule {
    @Provides
    @Named(OPEN_AI_API_KEY)
    fun provideApiKey(): String {
        return ApiKeyRetriever.getOpenAIApiKey()
    }

    @Provides
    @Singleton
    fun provideOpenAI(@Named(OPEN_AI_API_KEY) apiKey: String): OpenAI {
        val config = OpenAIConfig(token = apiKey)
        return OpenAI(config)
    }

    @Provides
    @Singleton
    fun provideOpenAIDatasource(openAI: OpenAI): OpenAIDatasource {
        return RemoteOpenAIDatasource(openAI)
    }
}

private const val OPEN_AI_API_KEY = "OPENAI_API_KEY"
