package com.migueldev.wodwiseapp.domain.exception

class FirestoreUnknownErrorException(cause: Throwable) :
    Exception("An unknown error occurred", cause)
