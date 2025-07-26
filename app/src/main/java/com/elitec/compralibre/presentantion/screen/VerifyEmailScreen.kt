package com.elitec.compralibre.presentantion.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.elitec.compralibre.presentantion.viewmodels.AuthViewmodel

@Composable
fun VerifyEmailScreen(
    email: String,
    navigateTo: (Boolean)-> Unit,
    authViewModel: AuthViewmodel,
    modifier: Modifier = Modifier
) {
    var verificationCode by rememberSaveable { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Verificar Email",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Subtítulo
        Text(
            text = "Ingresa el código enviado a $email",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Campo de código de verificación
        TextField(
            value = verificationCode,
            onValueChange = { verificationCode = it },
            label = { Text("Código de verificación") },
            isError = errorMessage.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorTextColor = MaterialTheme.colorScheme.error,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de error o éxito
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        if (successMessage.isNotEmpty()) {
            Text(
                text = successMessage,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Botón de verificar
        Button(
            onClick = {
                /*viewModelScope.launch {
                    try {
                        val user = account.get()
                        authViewModel.verifyEmail(
                            userId = user.id,
                            secret = verificationCode,
                            onSuccess = {
                                successMessage = "Email verificado exitosamente"
                                errorMessage = ""
                                navController.navigate("home")
                            },
                            onFailure = { message ->
                                errorMessage = message
                                successMessage = ""
                            }
                        )
                    } catch (e: Exception) {
                        errorMessage = "Error al obtener usuario: ${e.message}"
                        successMessage = ""
                    }
                }*/
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(4.dp, RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Verificar",
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón para reenviar código
        TextButton(
            onClick = {
                /*viewModelScope.launch {
                    try {
                        account.createVerification(
                            url = "https://[YOUR_REDIRECT_URL]/verify"
                        )
                        successMessage = "Código reenviado a $email"
                        errorMessage = ""
                    } catch (e: Exception) {
                        errorMessage = "Error al reenviar código: ${e.message}"
                        successMessage = ""
                    }
                }*/
            }
        ) {
            Text(
                text = "Reenviar código",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}