package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun ImageLogo(loginState: LoginState, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = loginState.appName,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 64.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = loginState.appSloganText,
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_round),
            contentDescription = loginState.descriptionLogoApp,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(Dimension.d128)
                .align(Alignment.CenterHorizontally)
        )
    }
}
