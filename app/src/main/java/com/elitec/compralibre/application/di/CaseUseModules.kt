package com.elitec.compralibre.application.di

import android.content.Context
import com.elitec.compralibre.domain.caseUser.accounts.AccountsManager
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthProvider
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthUiProvider
import com.elitec.compralibre.domain.caseUser.auth.users.UsersManagement
import com.elitec.compralibre.domain.repository.UserRepo
import com.elitec.compralibre.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.services.Account
import io.appwrite.services.Databases
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CaseUseModules {
    @Singleton
    @Provides
    fun getUserManagementInstance(userRepo: UserRepository, appUserpo: UserRepo): UsersManagement {
        return UsersManagement(userRepo,appUserpo)
    }

    @Singleton
    @Provides
    fun getAccountMangerInstance(
        @ApplicationContext context: Context,
        account: Account,
        httpClient: HttpClient,
        _googleAuthProvider: GoogleAuthProvider,
        databases: Databases
    ): AccountsManager {
        return AccountsManager(context,account,databases,httpClient,_googleAuthProvider)
    }
}