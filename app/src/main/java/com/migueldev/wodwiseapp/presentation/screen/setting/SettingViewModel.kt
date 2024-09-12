package com.migueldev.wodwiseapp.presentation.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val userPreferences: UserPreferences,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _settingState = MutableStateFlow(SettingState())
    val state: StateFlow<SettingState> = _settingState

    init {
        initializeTextResources()
    }

    fun initializeTextResources() {
        _settingState.update { currentState ->
            currentState.copy(
                settingTitleText = resourceProvider.getString(
                    R.string.setting_title_text
                ),
                themeSettingText = resourceProvider.getString(
                    R.string.theme_setting_text
                )
            )
        }
    }

    fun switchTheme() {
        viewModelScope.launch(ioDispatcher) {
            val newThemeMode = if (_settingState.value.mode.themeMode == ThemeSwitcher.LIGHT) {
                ThemeSwitcher.DARK
            } else {
                ThemeSwitcher.LIGHT
            }

            userPreferences.setDarkMode(newThemeMode == ThemeSwitcher.DARK)
            _settingState.update { it.copy(mode = ThemeSwitcher(newThemeMode, true)) }
        }
    }

    fun observeThemeMode() {
        viewModelScope.launch(ioDispatcher) {
            userPreferences.isDarkMode.collect { isDarkMode ->
                val themeMode = if (isDarkMode) ThemeSwitcher.DARK else ThemeSwitcher.LIGHT
                _settingState.update { it.copy(mode = ThemeSwitcher(themeMode, false)) }
            }
        }
    }
}
