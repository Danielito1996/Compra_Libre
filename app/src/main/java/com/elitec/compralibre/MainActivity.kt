package com.elitec.compralibre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elitec.compralibre.presentantion.components.CollapsibleBottomSheet
import com.elitec.compralibre.presentantion.components.ConnectionStatusView
import com.elitec.compralibre.presentantion.components.GettingStartedCards
import com.elitec.compralibre.presentantion.components.TopPlatformView
import com.elitec.compralibre.presentantion.components.addCheckeredBackground
import com.elitec.compralibre.presentantion.navigation.MainNavWrapper
import com.elitec.compralibre.presentantion.screen.theme.CompraLibreTheme
import com.elitec.compralibre.presentantion.viewmodels.AppwriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Surface para el fondo que cubre toda la pantalla
            val appwriteViewModel: AppwriteViewModel = viewModel()

            val logs by appwriteViewModel.logs.collectAsState()
            val status by appwriteViewModel.status.collectAsState()

            // data doesn't change, so no `remember`.
            val projectInfo = appwriteViewModel.getProjectInfo()

            CompraLibreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Scaffold con padding basado en WindowInsets
                    Scaffold(
                        containerColor = Color.Transparent,
                        modifier = Modifier.fillMaxSize(),
                        content = { innerPadding ->
                            MainNavWrapper(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding) // Padding del Scaffold
                                    .windowInsetsPadding(WindowInsets.systemBars) // Añade padding para barras del sistema
                            )
                        }
                    )
                }
                /*
                Scaffold(bottomBar = {
                    CollapsibleBottomSheet(
                        logs = logs,
                        projectInfo = projectInfo
                    )
                }) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .addCheckeredBackground()
                            .padding(innerPadding)
                            .windowInsetsPadding(WindowInsets.systemBars)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TopPlatformView(
                            status = status
                        )

                        ConnectionStatusView(status) {
                            appwriteViewModel.ping()
                        }

                        GettingStartedCards()
                    }
                }*/
            }
        }
    }
}