package com.migueldev.wodwiseapp.domain.repository.weight

import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.data.remote.datasource.weight.WeightsDatasource
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WeightRepositoryTest {

    private val weightDatasource = relaxedMockk<WeightsDatasource>()

    private lateinit var weightRepository: WeightRepository

    @BeforeEach
    fun setUp() {
        weightRepository = WeightRepository(weightDatasource)
    }

    @Test
    fun `GIVEN weights data source WHEN getWeights is called THEN return flow of ExerciseWeight list`() =
        runTest {
            val weightId1 = "1"
            val nameExercise1 = "Deadlift"
            val rm1 = 180.0
            val weightId2 = "2"
            val nameExercise2 = "Bench press"
            val rm2 = 150.0
            val weightData = listOf(
                WeightData(weightId1, nameExercise1, rm1),
                WeightData(weightId2, nameExercise2, rm2)
            )
            coEvery { weightDatasource.getWeights() } returns flowOf(weightData)

            val result: Flow<List<WeightData>> = weightRepository.getWeights()

            val collectedWeights = result.toList()
            collectedWeights shouldHaveSize 1
            collectedWeights.first() shouldBeEqualTo weightData
            coVerifyOnce { weightDatasource.getWeights() }
        }

    @Test
    fun `GIVEN valid dto WHEN addWeightToFirestore is called THEN datasource addWeightToFirestore is called`() =
        runTest {
            val weightId = "1"
            val nameExercise = "Deadlift"
            val rm = 180.0
            val exerciseWeightDto = WeightDto(weightId, nameExercise, rm)
            coEvery { weightDatasource.addWeightToFirestore(weightId, exerciseWeightDto) } just Runs

            weightRepository.addWeightToFirestore(weightId, exerciseWeightDto)

            coVerifyOnce { weightDatasource.addWeightToFirestore(weightId, exerciseWeightDto) }
        }

    @Test
    fun `GIVEN weightId WHEN removeWeight is called THEN datasource removeWeight is called`() =
        runTest {
            val weightId = "1"
            coEvery { weightDatasource.removeWeight(weightId) } just Runs

            weightRepository.removeWeight(weightId)

            coVerifyOnce { weightDatasource.removeWeight(weightId) }
        }

    @Test
    fun `GIVEN weightId and new RM WHEN updateWeightRm is called THEN datasource updateWeightRm is called`() =
        runTest {
            val weightId = "1"
            val newRm = 185.0
            coEvery { weightDatasource.updateWeightRm(weightId, newRm) } just Runs

            weightRepository.updateWeightRm(weightId, newRm)

            coVerifyOnce { weightDatasource.updateWeightRm(weightId, newRm) }
        }

    @Test
    fun `GIVEN deleteWeightsCollection WHEN deleteWeightsCollection THEN deleteWeightsCollection is called`() =
        runTest {
            coEvery { weightDatasource.deleteWeightsCollection() } just Runs

            weightRepository.deleteWeightsCollection()

            coVerifyOnce { weightDatasource.deleteWeightsCollection() }
        }

    @Test
    fun `GIVEN weight data WHEN addWeightHistoryToFirestore is called THEN datasource addWeightHistoryToFirestore`() =
        runTest {
            val weightId = "1"
            val weight = 80.0
            val repetitions = 10
            val date = "2024-10-20"
            coEvery {
                weightDatasource.addWeightHistoryToFirestore(weightId, weight, repetitions, date)
            } just Runs

            weightRepository.addWeightHistoryToFirestore(weightId, weight, repetitions, date)

            coVerifyOnce {
                weightDatasource.addWeightHistoryToFirestore(weightId, weight, repetitions, date)
            }
        }

    @Test
    fun `GIVEN weightId and idHistory WHEN removeWeightHistory is called THEN datasource removeWeightHistory`() =
        runTest {
            val weightId = "1"
            val idHistory = "history123"
            coEvery {
                weightDatasource.removeWeightHistory(weightId, idHistory)
            } just Runs

            weightRepository.removeWeightHistory(weightId, idHistory)

            coVerifyOnce {
                weightDatasource.removeWeightHistory(weightId, idHistory)
            }
        }
}
