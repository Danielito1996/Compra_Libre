package com.elitec.compralibre.presentantion.viewmodels

import android.app.Activity
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elitec.compralibre.domain.caseUser.accounts.AccountsManager
import com.elitec.compralibre.domain.entities.typeOf.Roles
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appwrite.ID
import io.appwrite.enums.OAuthProvider
import io.appwrite.models.Users
import io.appwrite.models.toIso8601
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Clock
import javax.inject.Inject
import kotlin.fold

@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val _accountManager: AccountsManager
): ViewModel() {

    private var _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState get() = _authState.asStateFlow()

    fun startOAuthLogin(activity: Activity) {
        viewModelScope.launch {
            _accountManager.createOAuth2Session(activity, OAuthProvider.GOOGLE,
                listOf("profile", "email")
            ).fold(
                onSuccess = { user ->
                    println("Usuario autenticado: $user, Foto: ${user.photoUrl}")
                },
                onFailure = { error ->
                    println("Error de autenticación: ${error.message}")
                }
            )
        }
    }

    fun register(
        name: String,
        email: String,
        pass: String,
        passConfirm: String,
        onSuccess: ()-> Unit,
        onFailure: ()-> Unit
    ) {
        val isValid = validateForm(
            fullName = name,
            email = email,
            password = pass,
            confirmPassword = passConfirm
        )
        if(isValid.isNotEmpty()) {
            // Notificador
            return
        }
        try {
            viewModelScope.launch {
                val response = _accountManager.register(
                    user = Users(
                        uid = ID.unique(),
                        email = email,
                        password = pass,
                        displayName = name,
                        photoUrl = "",
                        role = Roles.CUSTOM_USER.name,
                        createdAt = System.currentTimeMillis().toIso8601(),
                    )
                )
                if(response.isSuccess) {
                    onSuccess()
                } else {
                    onFailure()
                }
            }
        } catch (e: Exception) {
            println(e.localizedMessage?:"")
        }
    }

    fun register(context: Context) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading("Registrando usuario")
            try {
                val response = _accountManager.googleRegister(context)
                if(response.isSuccess) {
                    _authState.value = AuthState.Success(Icons.Default.VerifiedUser,"Usuario registrado correctamente")
                } else {
                    _authState.value = AuthState.OnError("Error al registrar usuario")
                }
            } catch (_: Exception) {
                _authState.value = AuthState.OnError("Error al registrar usuario")
            }
        }
    }


    fun validateForm(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): String {
        val errors = mutableListOf<String>()

        // Validar nombre completo
        if (fullName.isBlank()) {
            errors.add("El nombre completo no puede estar vacío")
        } else {
            val nameParts = fullName.trim().split("\\s+".toRegex())
            if (nameParts.size < 2) {
                errors.add("El nombre completo debe contener al menos nombre y apellido")
            } else if (nameParts.any { it.length < 3 }) {
                errors.add("Cada parte del nombre debe tener al menos 3 caracteres")
            } else if (!fullName.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+\$"))) {
                errors.add("El nombre completo solo puede contener letras y espacios")
            }
        }

        // Validar email
        if (email.isBlank()) {
            errors.add("El email no puede estar vacío")
        } else if (!email.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"))) {
            errors.add("El email no tiene un formato válido")
        }

        // Validar contraseña
        if (password.length < 8) {
            errors.add("La contraseña debe tener al menos 8 caracteres")
        }
        if (!password.any { it.isUpperCase() }) {
            errors.add("La contraseña debe contener al menos una letra mayúscula")
        }
        if (!password.any { it.isLowerCase() }) {
            errors.add("La contraseña debe contener al menos una letra minúscula")
        }
        if (!password.any { it.isDigit() }) {
            errors.add("La contraseña debe contener al menos un número")
        }
        if (!password.contains(Regex("[!@#\$%^&*()_\\-+={}|[\\]:;\"'<>,.?/~`]]"))) {
            errors.add("La contraseña debe contener al menos un carácter especial (!@#\$%^&*)")
        }
        if (password.contains(" ")) {
            errors.add("La contraseña no debe contener espacios")
        }

        // Validar confirmación de contraseña
        if (password != confirmPassword) {
            errors.add("Las contraseñas no coinciden")
        }

        // Devolver mensaje en blanco si no hay errores, o los errores concatenados
        return if (errors.isEmpty()) "" else errors.joinToString("\n")
    }
}

sealed class AuthState {
    object Idle: AuthState()
    data class Loading(val message: String): AuthState()
    data class OnError(val message: String): AuthState()
    data class Success(val icon: ImageVector,val message: String): AuthState()
}