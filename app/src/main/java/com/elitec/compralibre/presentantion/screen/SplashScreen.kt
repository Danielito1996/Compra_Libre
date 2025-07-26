package com.elitec.compralibre.presentantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.navigation.Login
import com.elitec.compralibre.presentantion.navigation.MainScreen
import com.elitec.compralibre.presentantion.navigation.Presentation
import com.elitec.compralibre.presentantion.util.checkIsFirstTime
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateTo: (MainScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isFirstTime = checkIsFirstTime(context)
    var showOnboarding by remember { mutableStateOf(isFirstTime) }

    LaunchedEffect(null) {
        delay(2000)
        if (showOnboarding) {
            navigateTo(Presentation)
        } else {
            // Contenido principal de la app
            navigateTo(Login)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = painterResource(R.drawable.pregoneros_clean),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Text(
            style = MaterialTheme.typography.labelSmall,
            text = "@bc"
        )
    }
}
