package com.migueldev.wodwiseapp.domain.usecase

import com.migueldev.wodwiseapp.domain.providers.TimeProvider
import javax.inject.Inject

class GenerateWorkoutIdUseCase @Inject constructor(
    private val timeProvider: TimeProvider,
) {
    operator fun invoke(): String {
        return timeProvider.currentTimeMillis().toString()
    }
}
