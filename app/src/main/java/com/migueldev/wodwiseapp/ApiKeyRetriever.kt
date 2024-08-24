package com.migueldev.wodwiseapp

object ApiKeyRetriever {

    init {
        System.loadLibrary("api-keys")
    }

    external fun getOpenAIApiKey(): String
}
