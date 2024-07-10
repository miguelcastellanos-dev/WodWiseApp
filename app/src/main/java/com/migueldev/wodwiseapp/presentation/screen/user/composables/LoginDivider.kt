package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .height(Dimension.d1)
                .weight(1f)
        )
        Text(
            text = stringResource(id = R.string.login_divider_text),
            modifier = Modifier.padding(horizontal = Dimension.d16),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .height(Dimension.d1)
                .weight(1f)
        )
    }
}
