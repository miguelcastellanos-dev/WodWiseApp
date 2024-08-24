package com.migueldev.wodwiseapp.domain.exception

class ConvertStringToLocalDateException(cause: Throwable? = null) :
    Exception("Error parsing date:", cause)
