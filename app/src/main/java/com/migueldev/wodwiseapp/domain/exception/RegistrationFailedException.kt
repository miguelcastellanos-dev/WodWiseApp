package com.migueldev.wodwiseapp.domain.exception

class RegistrationFailedException(cause: Throwable? = null) :
    Exception("Registration failed", cause)
