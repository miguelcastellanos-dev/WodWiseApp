package com.migueldev.wodwiseapp.domain.exception

class AddWeightHistoryException(weightId: String, cause: Throwable? = null) :
    Exception("Failed to add weight history for ID: $weightId", cause)
