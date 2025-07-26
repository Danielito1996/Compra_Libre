package com.elitec.compralibre.domain.caseUser.accounts

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.activity.ComponentActivity
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthProvider
import com.elitec.compralibre.domain.caseUser.auth.google.GoogleAuthUiProvider
import com.elitec.compralibre.domain.caseUser.auth.google.decodeJWT
import com.elitec.compralibre.domain.entities.typeOf.Roles
import dagger.hilt.android.qualifiers.ApplicationContext
import io.appwrite.ID
import io.appwrite.enums.OAuthProvider
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.UserAuth
import io.appwrite.models.Users
import io.appwrite.services.Account
import io.appwrite.services.Databases
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import kotlinx.serialization.Serializable
import java.time.Instant
import javax.inject.Inject

class AccountsManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val account: Account,
    private val databases: Databases,
    private val httpClient: HttpClient,
    private val _googleAuthProvider: GoogleAuthProvider,
) {
    suspend fun register(user: Users): Result<Any> {
        return try {
            val response = account.create(
                userId = ID.unique(),
                email = user.email ?: "",
                password = user.password,
                name = user.displayName
            )
            val userCreated = response
            val response2 = databases.createDocument(
                databaseId = "6882c5f00003405f4662",
                collectionId = "688307e700054c48df69",
                documentId = ID.unique(),
                data = UserAuth(
                    id = userCreated.id,
                    name = user.displayName ?: "",
                    email = user.email ?: "",
                    phone = userCreated.phone,
                    photoUrl = user.photoUrl,
                    createAt = userCreated.createdAt,
                    role = Roles.CUSTOM_USER.name
                ).toDocumentData()
            )
            Result.success(Any())
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    suspend fun googleRegister(context2: Context): Result<Unit> {
        return try {
            // Obtener la credencial de Google
            val response = _googleAuthProvider.getUiProvider(context2).signIn(context2)
                ?: return Result.failure(Exception("No Google credential obtained"))

            println("Response: $response")

            val id = ID.unique()

            databases.createDocument(
                databaseId = "6882c5f00003405f4662",
                collectionId = "688307e700054c48df69",
                documentId = id,
                data = UserAuth(
                    id = id,
                    name = response.displayName ?: "",
                    email = decodeJWT(response.idToken).getClaim("email").asString(),
                    phone = response.phoneNumber,
                    photoUrl = response.profilePictureUri.toString(),
                    role = Roles.CUSTOM_USER.name
                ).toDocumentData()
            )
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createOAuth2Session(
        activity: Activity,
        provider: OAuthProvider = OAuthProvider.GOOGLE,
        scopes: List<String> = listOf("repo", "user")
    ): Result<Users> {
        return try {

            account.createOAuth2Session(
                activity = activity as ComponentActivity,
                provider = provider,
                scopes = scopes
            )
            // Obtener la información del usuario autenticado
            val appwriteUser = account.get()
            val jwt = account.createJWT().jwt
            val photoUrl = decodeJWT(jwt).getClaim("profilePictureUri").asString()

            val id = ID.unique()

            databases.createDocument(
                databaseId = "6882c5f00003405f4662",
                collectionId = "688307e700054c48df69",
                documentId = id,
                data = UserAuth(
                    id = id,
                    name = appwriteUser.name,
                    email = appwriteUser.email,
                    phone = appwriteUser.phone,
                    photoUrl = photoUrl,
                    createAt = appwriteUser.createdAt,
                    role = Roles.CUSTOM_USER.name
                ).toDocumentData()
            )

            Result.success(
                Users(
                    uid = appwriteUser.id,
                    email = appwriteUser.email,
                    displayName = appwriteUser.name,
                    photoUrl = photoUrl,
                    role = Roles.CUSTOM_USER.name,
                    password = appwriteUser.password ?: "",
                    createdAt = appwriteUser.registration
                )
            )
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }



    private suspend fun getGoogleAvatarUrl(jwt: String): String? {
        return try {
            val response: GooglePeopleResponse = httpClient.get("https://people.googleapis.com/v1/people/me?personFields=photos") {
                headers {
                    append("Authorization", "Bearer $jwt")
                }
            }.body()
            response.photos?.firstOrNull()?.url
        } catch (e: Exception) {
            null
        }
    }

    private fun String.toLongTimestamp(): Long {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Instant.parse(this).toEpochMilli()
            } else {
                TODO("VERSION.SDK_INT < O")
            }
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
}

@Serializable
data class GooglePeopleResponse(
    val photos: List<GooglePhoto>?
)

@Serializable
data class GooglePhoto(
    val url: String
)