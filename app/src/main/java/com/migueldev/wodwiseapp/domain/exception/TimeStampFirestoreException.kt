package com.migueldev.wodwiseapp.domain.exception

class TimeStampFirestoreException(
    cause: Throwable? = null,
) : Exception("Error formatting timestamp", cause)
