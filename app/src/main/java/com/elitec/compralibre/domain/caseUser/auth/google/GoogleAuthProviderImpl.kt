package com.elitec.compralibre.domain.caseUser.auth.google

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import javax.inject.Inject

class GoogleAuthProviderImpl @Inject constructor(
    private val credentialManager: CredentialManager
): GoogleAuthProvider {
    override fun getUiProvider(context: Context): GoogleAuthUiProviderImpl {
        return GoogleAuthUiProviderImpl(credentialManager)
    }

    override suspend fun signOut() {
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}