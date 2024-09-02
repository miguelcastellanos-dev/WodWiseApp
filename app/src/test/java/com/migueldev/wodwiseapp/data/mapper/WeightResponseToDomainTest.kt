package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.remote.response.WeightResponse
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class WeightResponseToDomainTest {

    @Test
    fun `GIVEN WeightResponse WHEN toDomain is called THEN return correct ExerciseWeight`() {
        val weightResponse = WeightResponse(
            weightId = "123",
            nameExercise = "Bench press",
            repetitionMaximum = 120.0
        )
        val expectedWeightData = WeightData(
            weightId = "123",
            weightName = "Bench press",
            weightRepetitionMaximum = 120.0
        )

        val result = weightResponse.toDomain()

        result shouldBeEqualTo expectedWeightData
    }
}
