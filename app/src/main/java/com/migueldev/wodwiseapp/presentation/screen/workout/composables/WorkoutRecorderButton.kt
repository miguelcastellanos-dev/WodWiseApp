package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import android.content.Context
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.migueldev.wodwiseapp.presentation.navigation.AppButtonsState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.transparentRed
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@Composable
fun WorkoutRecorderButton(
    workoutState: WorkoutState,
    context: Context,
    appButtonsState: AppButtonsState,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RecorderButtonTransition")
    val animatedColor by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = transparentRed,
        animationSpec = infiniteRepeatable(
            animation = tween(ANIMATION_DURATION),
            repeatMode = RepeatMode.Reverse
        ),
        label = "AnimatedButtonColor"
    )
    Button(
        onClick = { appButtonsState.onRequestPermissionAndRecordClicked(context) },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (workoutState.isRecording) {
                animatedColor
            } else {
                MaterialTheme.colorScheme.primaryContainer
            },
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer),
        enabled = workoutState.isRecorderButtonEnabled
    ) {
        Icon(
            imageVector = Icons.Default.Mic,
            contentDescription = "Mic icon",
            modifier = Modifier.size(Dimension.d24)
        )
        Spacer(modifier = Modifier.width(Dimension.d8))
        Text(
            text = if (workoutState.isRecording) {
                workoutState.stopRecordingText
            } else {
                workoutState.startRecordingText
            },
            style = MaterialTheme.typography.titleLarge
        )
    }
}

private const val ANIMATION_DURATION = 750
