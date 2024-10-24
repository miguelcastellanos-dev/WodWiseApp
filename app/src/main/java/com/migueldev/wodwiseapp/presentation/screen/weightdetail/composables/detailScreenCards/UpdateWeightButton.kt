package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun UpdateWeightButton(
    onButtonClick: () -> Unit,
    weightDetailState: WeightDetailState,
    exerciseRm: Double,
) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .shadow(
                elevation = Dimension.d1,
                shape = RoundedCornerShape(Dimension.d64),
                spotColor = MaterialTheme.colorScheme.primary
            )
            .padding(Dimension.d8),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = "${weightDetailState.exerciseRmTitleText} $exerciseRm",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(Dimension.d8))
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit",
            modifier = Modifier.size(Dimension.d24)
        )
    }
}
