package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightHistoryData
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class WeightDtoToMapTest {

    @Test
    fun `GIVEN ExerciseWeightDto WHEN toMap is called THEN return correct map`() {
        val weightHistoryExample = listOf(
            WeightHistoryData(
                dateHistory = "2023-10-01",
                weightHistory = 80.0,
                repetitionsHistory = 10,
                idHistory = ""
            ),
            WeightHistoryData(
                dateHistory = "2023-10-02",
                weightHistory = 85.0,
                repetitionsHistory = 8,
                idHistory = ""
            )
        )
        val exerciseWeightDto = WeightDto(
            weightId = "123",
            nameExercise = "Bench press",
            repetitionMaximum = 85.5,
            weightHistoryList = weightHistoryExample
        )
        val expectedMap = mapOf(
            "weightId" to "123",
            "nameExercise" to "Bench press",
            "repetitionMaximum" to 85.5,
            "weightHistoryList" to listOf(
                mapOf(
                    "dateHistory" to "2023-10-01",
                    "weightHistory" to 80.0,
                    "repetitionsHistory" to 10,
                    "idHistory" to ""
                ),
                mapOf(
                    "dateHistory" to "2023-10-02",
                    "weightHistory" to 85.0,
                    "repetitionsHistory" to 8,
                    "idHistory" to ""
                )
            )
        )

        val result = exerciseWeightDto.toMap()

        result shouldBeEqualTo expectedMap
    }
}
