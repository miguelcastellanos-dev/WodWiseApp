package com.migueldev.wodwiseapp.presentation.screen.scaffold

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val resourceProvider: ResourceProvider,
    private val logger: Logger,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _scaffoldState = MutableStateFlow(ScaffoldState())
    val state: StateFlow<ScaffoldState> = _scaffoldState

    init {
        initializeTextResources()
        observeThemeMode()
    }

    fun initializeTextResources() {
        _scaffoldState.update { currentState ->
            currentState.copy(
                profileItemText = resourceProvider.getString(
                    R.string.profile_item
                ),
                settingsItemText = resourceProvider.getString(
                    R.string.settings_item
                ),
                signOffItemText = resourceProvider.getString(
                    R.string.sign_off_item
                ),
                scaffoldTitleText = resourceProvider.getString(
                    R.string.scaffold_title
                ),
                calendarIconText = resourceProvider.getString(
                    R.string.calendar_icon_text
                ),
                addWorkoutIconText = resourceProvider.getString(
                    R.string.add_workout_icon_text
                ),
                weightsIconText = resourceProvider.getString(
                    R.string.weights_icon_text
                ),
                aIIconText = resourceProvider.getString(
                    R.string.a_i_icon_text
                )
            )
        }
    }

    private fun observeThemeMode() {
        viewModelScope.launch(ioDispatcher) {
            userPreferences.isDarkMode.collect { isDarkMode ->
                val themeMode = if (isDarkMode) ThemeSwitcher.DARK else ThemeSwitcher.LIGHT
                _scaffoldState.update { it.copy(mode = ThemeSwitcher(themeMode, false)) }
            }
        }
    }

    fun switchTheme() {
        viewModelScope.launch(ioDispatcher) {
            val newThemeMode = if (_scaffoldState.value.mode.themeMode == ThemeSwitcher.LIGHT) {
                ThemeSwitcher.DARK
            } else {
                ThemeSwitcher.LIGHT
            }

            userPreferences.setDarkMode(newThemeMode == ThemeSwitcher.DARK)
            _scaffoldState.update { it.copy(mode = ThemeSwitcher(newThemeMode, true)) }
        }
    }

    fun logger(tag: String, message: String) {
        logger.d(tag, message)
    }
}
