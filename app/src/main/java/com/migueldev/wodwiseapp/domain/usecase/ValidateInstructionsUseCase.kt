package com.migueldev.wodwiseapp.domain.usecase

import javax.inject.Inject

class ValidateInstructionsUseCase @Inject constructor() {
    operator fun invoke(instructions: String): String {
        return instructions.ifBlank { "Empty workout" }
    }
}
