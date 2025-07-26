package com.elitec.compralibre.application.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthProvider
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthProviderImpl
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthUiProvider
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthUiProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModules {
    @Provides
    @Singleton
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @Singleton
    fun getGoogleAuthProvider(credentialManager: CredentialManager): GoogleAuthProvider {
        return GoogleAuthProviderImpl(credentialManager)
    }

    @Provides
    @Singleton
    fun getGoogleAuthUIProvider(credentialManager: CredentialManager): GoogleAuthUiProvider {
        return GoogleAuthUiProviderImpl(credentialManager)
    }

    @Provides
    @Singleton
    fun getGoogleLibrearyProvider(): com.mmk.kmpauth.google.GoogleAuthProvider {
        return com.mmk.kmpauth.google.GoogleAuthProvider.create(credentials = com.mmk.kmpauth.google.GoogleAuthCredentials(serverId = "861734761304-6ls42ied1p1n4m6fv8rbke8q4q26ph7s.apps.googleusercontent.com"))
    }
}