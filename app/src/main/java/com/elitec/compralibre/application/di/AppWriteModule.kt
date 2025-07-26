package com.elitec.compralibre.application.di

import android.content.Context
import com.elitec.compralibre.data.dataModels.AppWriteDataBaseInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import io.appwrite.services.Account
import io.appwrite.services.Databases
import javax.inject.Singleton

const val APPWRITE_PROJECT_ID = "6882aea2001e0b18d142" // Colocar en gradle properties
const val APPWRITE_ENDPOINT = "https://fra.cloud.appwrite.io/v1"

@Module
@InstallIn(SingletonComponent::class)
class AppWriteModule {
    @Provides
    @Singleton
    fun getAppWriteInstance(@ApplicationContext context: Context): Client {
        return Client(context.applicationContext)
            .setProject(APPWRITE_PROJECT_ID)
            .setEndpoint(APPWRITE_ENDPOINT)
    }

    @Provides
    @Singleton
    fun getAppWriteAuthInstance(client: Client): Account {
        return Account(client)
    }

    @Provides
    @Singleton
    fun getAppWriteDatabaseInstance(client: Client): Databases {
        return Databases(client)
    }

    @Provides
    @Singleton
    fun getAppWriteDatabaseInfoInstance(client: Client): AppWriteDataBaseInfo {
        return AppWriteDataBaseInfo()
    }
}