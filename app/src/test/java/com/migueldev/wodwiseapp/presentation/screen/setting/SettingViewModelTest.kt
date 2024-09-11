package com.migueldev.wodwiseapp.presentation.screen.setting

import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingViewModelTest {

    private val userPreferences = relaxedMockk<UserPreferences>()
    private val testDispatcher = UnconfinedTestDispatcher()
    private val isDarkModeFlow = MutableStateFlow(false)

    private lateinit var viewModel: SettingViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        every { userPreferences.isDarkMode } returns isDarkModeFlow
        viewModel = SettingViewModel(
            userPreferences = userPreferences,
            ioDispatcher = testDispatcher
        )
        viewModel.observeThemeMode()
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
            advanceUntilIdle()

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.DARK
        }

    @Test
    fun `GIVEN observeThemeMode is called WHEN isDarkMode is false THEN state is updated to LIGHT mode`() =
        runTest {
            isDarkModeFlow.emit(false)
            advanceUntilIdle()

            val result = viewModel.state.value
            result.mode.themeMode shouldBeEqualTo ThemeSwitcher.LIGHT
        }
}
