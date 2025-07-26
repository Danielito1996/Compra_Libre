package com.elitec.compralibre.presentantion.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elitec.compralibre.R
import com.elitec.compralibre.presentantion.navigation.MainScreen
import com.elitec.compralibre.presentantion.navigation.PassRecovery
import com.elitec.compralibre.presentantion.navigation.Register

@Composable
fun LoginScreen(
    navigateTo: (MainScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.pregoneros_clean),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Título
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Subtítulo
        Text(
            text = "Debe loguearse para continuar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Campo de email
        TextField(
            value = TextFieldValue(""), // Placeholder visual, sin lógica
            onValueChange = {},
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
            value = TextFieldValue(""), // Placeholder visual, sin lógica
            onValueChange = {},
            label = { Text("Contraseña") },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surface),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Olvidé mi contraseña (opcional)
        Text(
            text = "¿Ha olvidado su contraseña?",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.End)
                .clickable {
                    navigateTo(PassRecovery)
                }
        )
        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))

        // Botón de login
        Button(
            onClick = {}, // Sin lógica
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
                text = "Entrar",
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
        // Botón de Google
        Button(
            onClick = {}, // Placeholder, sin lógica
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
                    text = "Entrar con Google",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Opción de registro
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿No tiene cuenta? ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Registrese",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    navigateTo(Register)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navigateTo = {},
        modifier = Modifier.fillMaxSize()
    )
}