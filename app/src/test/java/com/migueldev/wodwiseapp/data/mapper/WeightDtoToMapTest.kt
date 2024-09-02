package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.dto.WeightDto
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class WeightDtoToMapTest {

    @Test
    fun `GIVEN ExerciseWeightDto WHEN toMap is called THEN return correct map`() {
        val exerciseWeightDto = WeightDto(
            weightId = "123",
            nameExercise = "Bench press",
            repetitionMaximum = 85.5
        )
        val expectedMap = mapOf(
            "weightId" to "123",
            "nameExercise" to "Bench press",
            "repetitionMaximum" to 85.5
        )

        val result = exerciseWeightDto.toMap()

        result shouldBeEqualTo expectedMap
    }
}
