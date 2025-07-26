package com.elitec.compralibre.domain.caseUser.auth.google

import android.content.Context
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential

interface GoogleAuthUiProvider {
    suspend fun signIn(context: Context):  GoogleIdTokenCredential?
}