package com.migueldev.wodwiseapp.presentation.screen.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.model.CreateRouteCalendarDetailParams
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val workoutRepository: WorkoutRepository,
    private val resourceProvider: ResourceProvider,
    private val toastWrapper: ToastWrapper,
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
                ),
                calendarChangesSavedToast = resourceProvider.getString(
                    R.string.calendar_detail_changes_saved_toast
                ),
                calendarInformationText = resourceProvider.getString(
                    R.string.calendar_information_text
                ),
                calendarInformationTitleText = resourceProvider.getString(
                    R.string.calendar_information_title_text
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

    fun getCheckboxState(workoutId: String): StateFlow<Boolean> {
        return state.map { it.checkboxState[workoutId] ?: false }
            .stateIn(viewModelScope, SharingStarted.Lazily, false)
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

    fun updateNotesText(workoutId: String, newNotes: String) {
        viewModelScope.launch(ioDispatcher) {
            val currentNotesText = state.value.notesText.toMutableMap()
            currentNotesText[workoutId] = newNotes
            _calendarStates.value = state.value.copy(notesText = currentNotesText)
            workoutRepository.updateNotesState(workoutId, newNotes)
        }
        toastWrapper.show(_calendarStates.value.calendarChangesSavedToast)
    }

    fun updateInstructionsText(workoutId: String, newInstructions: String) {
        viewModelScope.launch(ioDispatcher) {
            val currentInstructionsText = state.value.instructionsText.toMutableMap()
            currentInstructionsText[workoutId] = newInstructions
            _calendarStates.value = state.value.copy(instructionsText = currentInstructionsText)
            workoutRepository.updateInstructionsState(workoutId, newInstructions)
        }
        toastWrapper.show(_calendarStates.value.calendarChangesSavedToast)
    }

    fun sendWorkoutToDetail(navController: NavHostController): (WorkoutCardData) -> Unit {
        return { workout ->
            val positionSession = workout.positionSession.uppercase(Locale.ROOT)
            val exercise = workout.exerciseType.uppercase(Locale.ROOT)
            val encodedInstructions =
                URLEncoder.encode(workout.instructions, StandardCharsets.UTF_8.toString())
            val encodedNotes =
                URLEncoder.encode(workout.notes, StandardCharsets.UTF_8.toString())

            val params = CreateRouteCalendarDetailParams(
                positionSession = positionSession,
                exercise = exercise,
                instructions = encodedInstructions,
                workoutId = workout.workoutId,
                date = workout.date,
                session = workout.session,
                notes = encodedNotes
            )
            navController.navigate(Routes.CalendarDetailScreen.createRoute(params))
        }
    }
}
