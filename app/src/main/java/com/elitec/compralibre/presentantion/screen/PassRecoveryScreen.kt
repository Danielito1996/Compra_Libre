package com.elitec.compralibre.presentantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.util.isValidEmail

@Composable
fun PassRecoveryScreen(
    navigateTo: () -> Unit,
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ilustración unDraw
        Image(
            painter = painterResource(R.drawable.undraw_forgot_password_odai_gray),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 24.dp)
        )

        // Título
        Text(
            text = "Recupera tu Contraseña",
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 26.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Descripción
        Text(
            text = "Ingresa tu correo electrónico para recibir un enlace de restablecimiento.",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Campo de correo
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !isValidEmail(it)
            },
            label = { Text("Correo Electrónico") },
            isError = emailError,
            supportingText = {
                if (emailError) {
                    Text("Por favor, ingresa un correo válido", color = MaterialTheme.colorScheme.error)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
        )

        // Botón de Enviar
        Button(
            onClick = {
                if (isValidEmail(email)) {
                    /*onPasswordResetRequest(email)
                    scope.launch {
                        snackbarHostState.showSnackbar("Enlace de restablecimiento enviado a $email")
                    }*/
                    email = "" // Limpia el campo tras enviar
                } else {
                    emailError = true
                    /*scope.launch {
                        snackbarHostState.showSnackbar("Por favor, ingresa un correo válido")
                    }*/
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text("Enviar Enlace", fontSize = 18.sp)
        }

        // Enlace para volver al login
        TextButton(
            onClick = { navigateTo() }
        ) {
            Text(
                text = "Volver al Inicio de Sesión",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp
            )
        }
    }
}