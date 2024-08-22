package com.migueldev.wodwiseapp.domain.exception

class FirestoreConnectionException(cause: Throwable? = null) :
    Exception("Failed to connect to Firestore", cause)
