package com.migueldev.wodwiseapp.domain.providers

import javax.inject.Inject

interface TimeProvider {
    fun currentTimeMillis(): Long
}

class DefaultTimeProvider @Inject constructor() : TimeProvider {
    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}
