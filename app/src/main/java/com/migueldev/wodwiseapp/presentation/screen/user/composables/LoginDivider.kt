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
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun LoginDivider(loginState: LoginState) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .height(Dimension.d1)
                .weight(1f)
        )
        Text(
            text = loginState.loginDividerText,
            modifier = Modifier.padding(horizontal = Dimension.d16),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .height(Dimension.d1)
                .weight(1f)
        )
    }
}
