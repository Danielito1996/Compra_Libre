package com.elitec.compralibre.domain.caseUser.auth.google

import android.app.Activity
import android.content.Context
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GoogleAuthUiProviderImpl @Inject constructor(
    //private val activityContext: Context,
    private val credentialManager: CredentialManager
): GoogleAuthUiProvider {
    override suspend fun signIn(context: Context):  GoogleIdTokenCredential? =  withContext(Dispatchers.Main) {
        try {
            val credential = credentialManager.getCredential(
                context = context as Activity,
                request = getCredentialRequest()
            ).credential
            handleSignIn(credential)
        } catch (e: GetCredentialCancellationException) {
            println("Autenticación cancelada por el usuario: ${e.message}")
            null
        } catch (e: GetCredentialException) {
            println("Error de CredentialManager: ${e.message}")
            null
        } catch (e: Exception) {
            println("Error inesperado: ${e.message}")
            null
        }
    }

    private fun handleSignIn(credential: Credential):  GoogleIdTokenCredential? = when {
        credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL -> {
            try {
                GoogleIdTokenCredential.createFrom(credential.data)
            } catch (e: GoogleIdTokenParsingException) {
                null
            }
        }
        else -> null
    }

    private fun getCredentialRequest(): GetCredentialRequest =
        GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption())
            .build()

    private fun getGoogleIdOption(): GetGoogleIdOption = GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setAutoSelectEnabled(true)
        .setServerClientId("861734761304-6ls42ied1p1n4m6fv8rbke8q4q26ph7s.apps.googleusercontent.com")
        .build()
}