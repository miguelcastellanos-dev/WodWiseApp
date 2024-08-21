package com.migueldev.wodwiseapp.domain.exception

class LoginFailedException(cause: Throwable? = null) :
    Exception("Login failed", cause)
