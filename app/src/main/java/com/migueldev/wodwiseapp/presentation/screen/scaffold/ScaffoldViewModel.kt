package com.migueldev.wodwiseapp.presentation.screen.scaffold

import androidx.lifecycle.ViewModel
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ScaffoldViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val logger: Logger,
) : ViewModel() {

    private val _scaffoldState = MutableStateFlow(ScaffoldState())
    val state: StateFlow<ScaffoldState> = _scaffoldState

    init {
        initializeTextResources()
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
                coachIconText = resourceProvider.getString(
                    R.string.coach_icon_text
                )
            )
        }
    }

    fun logger(tag: String, message: String) {
        logger.d(tag, message)
    }
}
