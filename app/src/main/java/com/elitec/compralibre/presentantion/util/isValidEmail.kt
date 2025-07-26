package com.elitec.compralibre.presentantion.util

import android.util.Patterns

// Validación simple de correo
fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}