package com.elitec.compralibre.domain.caseUser.auth.google

import android.content.Context

interface GoogleAuthProvider {
    fun getUiProvider(context: Context): GoogleAuthUiProviderImpl
    suspend fun signOut()
}