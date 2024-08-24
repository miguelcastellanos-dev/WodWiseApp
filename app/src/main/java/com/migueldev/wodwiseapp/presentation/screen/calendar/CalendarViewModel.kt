package com.migueldev.wodwiseapp.presentation.screen.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val resourceProvider: ResourceProvider,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _calendarStates = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _calendarStates

    fun initializeState(state: CalendarState) {
        _calendarStates.value = state
    }

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _calendarStates.update { currentState ->
            currentState.copy(
                prefixTitleWeekText = resourceProvider.getString(
                    R.string.prefix_title_week
                ),
                instructionTextTitleInCardView = resourceProvider.getString(
                    R.string.instruction_text_title_in_card_view
                ),
                notesTextTitleInCardView = resourceProvider.getString(
                    R.string.notes_text_title_in_card_view
                )
            )
        }
    }

    fun getWorkoutsFromDatabase() {
        viewModelScope.launch(ioDispatcher) {
            _calendarStates.value = _calendarStates.value.copy(isLoading = true)
            workoutRepository.getWorkouts().collect { workoutsList ->
                val checkboxState = workoutsList.associate { it.workoutId to it.checkboxState }
                _calendarStates.update {
                    it.copy(
                        workoutsList = workoutsList,
                        checkboxState = checkboxState,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun updateCalendar(): List<WorkoutCardData> {
        return _calendarStates.value.workoutsList
    }

    fun updateCheckboxState(workoutId: String, newState: Boolean) {
        viewModelScope.launch(ioDispatcher) {
            _calendarStates.update { currentState ->
                val updatedMap = currentState.checkboxState.toMutableMap()
                updatedMap[workoutId] = newState
                currentState.copy(checkboxState = updatedMap)
            }
            workoutRepository.updateCheckboxState(workoutId, newState)
        }
    }

    fun foldOrUnfoldWeekTab(date: String) {
        _calendarStates.update { currentState ->
            val updatedMap = currentState.showWeekCardState.toMutableMap()
            val isCurrentlyVisible = updatedMap[date] ?: false
            updatedMap.keys.forEach { key -> updatedMap[key] = false }
            updatedMap[date] = !isCurrentlyVisible
            currentState.copy(showWeekCardState = updatedMap)
        }
    }

    fun foldOrUnfoldDayTab(date: String) {
        _calendarStates.update { currentState ->
            val updatedMap = currentState.showDayCardState.toMutableMap()
            val isCurrentlyVisible = updatedMap[date] ?: false
            updatedMap.keys.forEach { key -> updatedMap[key] = false }
            updatedMap[date] = !isCurrentlyVisible
            currentState.copy(showDayCardState = updatedMap)
        }
    }

    fun deleteWorkoutItem(id: String) {
        viewModelScope.launch(ioDispatcher) {
            workoutRepository.removeWorkout(id)
        }
    }
}
