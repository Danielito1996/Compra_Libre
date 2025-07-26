package com.elitec.compralibre.presentantion.screen

import android.app.Activity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.viewmodels.AuthViewmodel
import com.mmk.kmpauth.google.GoogleButtonUiContainer

@Composable
fun RegisterScreen(
    authViewmodel: AuthViewmodel,
    modifier: Modifier = Modifier
) {
    val currentContext = LocalContext.current
    val context = currentContext as? Activity ?: throw IllegalStateException("Context is not an Activity")

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var pass by rememberSaveable { mutableStateOf("") }
    var passConfirm by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text(
            text = "Crear cuenta",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Subtítulo
        Text(
            text = "Unete a nosotros y disfrute nuevas experiencias",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Campo de nombre
        TextField(
            value = name, // Placeholder visual, sin lógica
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de email
        TextField(
            value = email, // Placeholder visual, sin lógica
            onValueChange = { email = it },
            label = { Text("Correo") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña
        TextField(
            isError = pass.isNotEmpty() && validatePassword(pass).isNotEmpty(),
            value = pass, // Placeholder visual, sin lógica
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
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
        val validMessages = validatePassword(pass)
        if(pass.isNotEmpty() && validMessages.isNotEmpty()) {
            AnimatedContent(
                targetState = validMessages,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(
                    color = MaterialTheme.colorScheme.error,
                    text = it
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de confirmar contraseña
        TextField(
            isError = pass != passConfirm,
            value = passConfirm, // Placeholder visual, sin lógica
            onValueChange = { passConfirm = it },
            label = { Text("Confirme contraseña") },
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

        // Botón de registro
        Button(
            onClick = {
                authViewmodel.register(
                    name = name,
                    email = email,
                    passConfirm = passConfirm,
                    pass = pass,
                    onSuccess = {},
                    onFailure = {}
                )
            }, // Placeholder, sin lógica
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
                text = "Registrarse",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(4f).fillMaxWidth(),
                color = MaterialTheme.colorScheme.inversePrimary
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.weight(2f).fillMaxWidth(),
            ) {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    text = "O"
                )
            }
            HorizontalDivider(
                modifier = Modifier.weight(4f).fillMaxWidth(),
                color = MaterialTheme.colorScheme.inversePrimary
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewmodel.startOAuthLogin(context)//Por Google
            }, // Placeholder, sin lógica
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.googleicon), // Placeholder, reemplaza con ic_google
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Registrarse con Google",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Opción de login
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Ya posee alguna cuenta? ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Entrar",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

fun validatePassword(password: String): String {
    val errors = mutableListOf<String>()

    // Criterio 1: Longitud mínima de 8 caracteres
    if (password.length < 8) {
        errors.add("La contraseña debe tener al menos 8 caracteres")
    }

    // Criterio 2: Al menos una letra mayúscula
    if (!password.any { it.isUpperCase() }) {
        errors.add("La contraseña debe contener al menos una letra mayúscula")
    }

    // Criterio 3: Al menos una letra minúscula
    if (!password.any { it.isLowerCase() }) {
        errors.add("La contraseña debe contener al menos una letra minúscula")
    }

    // Criterio 4: Al menos un número
    if (!password.any { it.isDigit() }) {
        errors.add("La contraseña debe contener al menos un número")
    }

    // Criterio 5: Al menos un carácter especial
    val specialChars = "[!@#\$%^&*()_\\-+={}|[\\]:;\"'<>,.?/~`]]"
    if (!password.contains(Regex(specialChars))) {
        errors.add("La contraseña debe contener al menos un carácter especial (!@#\$%^&*)")
    }

    // Criterio 6: Sin espacios
    if (password.contains(" ")) {
        errors.add("La contraseña no debe contener espacios")
    }

    // Devolver mensaje en blanco si no hay errores, o los errores concatenados
    return if (errors.isEmpty()) {
        ""
    } else {
        errors.joinToString("\n")
    }
}
