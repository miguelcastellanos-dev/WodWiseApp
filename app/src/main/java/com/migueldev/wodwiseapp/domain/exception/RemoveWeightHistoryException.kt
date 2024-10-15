package com.migueldev.wodwiseapp.domain.exception

class RemoveWeightHistoryException(
    weightId: String,
    idHistory: String,
    cause: Throwable? = null,
) : Exception(
    "Failed to remove weight history for weight ID: $weightId and history ID: $idHistory",
    cause
)
