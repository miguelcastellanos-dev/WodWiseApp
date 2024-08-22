package com.migueldev.wodwiseapp.presentation.screen.scaffold

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
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
            userPreferences = userPreferences,
            logger = logger,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `GIVEN initial state is light theme WHEN switchTheme is called THEN mode should be dark`() =
        runTest(testDispatcher) {
            viewModel.switchTheme()

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.DARK
        }

    @Test
    fun `GIVEN initial state is light theme WHEN switchTheme is called two times THEN returns to light`() =
        runTest(testDispatcher) {
            viewModel.switchTheme()
            viewModel.switchTheme()

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.LIGHT
        }

    @Test
    fun `GIVEN observeThemeMode is called WHEN isDarkMode is true THEN state is updated to DARK mode`() =
        runTest {
            isDarkModeFlow.emit(true)

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.DARK
        }

    @Test
    fun `GIVEN observeThemeMode is called WHEN isDarkMode is false THEN state is updated to LIGHT mode`() =
        runTest {
            isDarkModeFlow.emit(false)

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.LIGHT
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
            val aIIconText = "AI"

            every { resourceProvider.getString(R.string.profile_item) } returns profileItemText
            every { resourceProvider.getString(R.string.settings_item) } returns settingsItemText
            every { resourceProvider.getString(R.string.sign_off_item) } returns signOffItemText
            every { resourceProvider.getString(R.string.scaffold_title) } returns scaffoldTitleText
            every { resourceProvider.getString(R.string.calendar_icon_text) } returns calendarIconText
            every { resourceProvider.getString(R.string.add_workout_icon_text) } returns addWorkoutIconText
            every { resourceProvider.getString(R.string.weights_icon_text) } returns weightsIconText
            every { resourceProvider.getString(R.string.a_i_icon_text) } returns aIIconText

            viewModel.initializeTextResources()

            val result = viewModel.state.value
            result.profileItemText shouldBeEqualTo profileItemText
            result.settingsItemText shouldBeEqualTo settingsItemText
            result.signOffItemText shouldBeEqualTo signOffItemText
            result.scaffoldTitleText shouldBeEqualTo scaffoldTitleText
            result.calendarIconText shouldBeEqualTo calendarIconText
            result.addWorkoutIconText shouldBeEqualTo addWorkoutIconText
            result.weightsIconText shouldBeEqualTo weightsIconText
            result.aIIconText shouldBeEqualTo aIIconText
        }
}
