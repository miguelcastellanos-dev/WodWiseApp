package com.migueldev.wodwiseapp.presentation.screen.scaffold

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ScaffoldViewModelTest {

    private val resourceProvider = relaxedMockk<ResourceProvider>()
    private val userPreferences = relaxedMockk<UserPreferences>()
    private val logger = relaxedMockk<Logger>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private val isDarkModeFlow = MutableStateFlow(false)

    private lateinit var viewModel: ScaffoldViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { userPreferences.isDarkMode } returns isDarkModeFlow
        viewModel = ScaffoldViewModel(
            resourceProvider = resourceProvider,
            logger = logger
        )
    }

    @Test
    fun `GIVEN initializeTextResources is called WHEN called THEN state is updated with correct texts`() =
        runTest(testDispatcher) {
            val profileItemText = "Profile"
            val settingsItemText = "Settings"
            val signOffItemText = "Sign off"
            val scaffoldTitleText = "WodWise"
            val calendarIconText = "Calendar"
            val addWorkoutIconText = "Add workout"
            val weightsIconText = "Weights"
            val coachIconText = "Coach"
            every { resourceProvider.getString(R.string.profile_item) } returns profileItemText
            every { resourceProvider.getString(R.string.settings_item) } returns settingsItemText
            every { resourceProvider.getString(R.string.sign_off_item) } returns signOffItemText
            every { resourceProvider.getString(R.string.scaffold_title) } returns scaffoldTitleText
            every { resourceProvider.getString(R.string.calendar_icon_text) } returns calendarIconText
            every { resourceProvider.getString(R.string.add_workout_icon_text) } returns addWorkoutIconText
            every { resourceProvider.getString(R.string.weights_icon_text) } returns weightsIconText
            every { resourceProvider.getString(R.string.coach_icon_text) } returns coachIconText

            viewModel.initializeTextResources()

            val result = viewModel.state.value
            result.profileItemText shouldBeEqualTo profileItemText
            result.settingsItemText shouldBeEqualTo settingsItemText
            result.signOffItemText shouldBeEqualTo signOffItemText
            result.scaffoldTitleText shouldBeEqualTo scaffoldTitleText
            result.calendarIconText shouldBeEqualTo calendarIconText
            result.addWorkoutIconText shouldBeEqualTo addWorkoutIconText
            result.weightsIconText shouldBeEqualTo weightsIconText
            result.coachIconText shouldBeEqualTo coachIconText
        }
}
