package com.migueldev.wodwiseapp.domain.exception

class OpenAIResponseNullException(cause: Throwable? = null) :
    Exception("Response is null or empty", cause)
