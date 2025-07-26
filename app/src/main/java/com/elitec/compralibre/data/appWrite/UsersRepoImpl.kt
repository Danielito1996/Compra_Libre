package com.elitec.compralibre.data.appWrite

import com.elitec.compralibre.data.dataModels.AppWriteDataBaseInfo
import com.elitec.compralibre.domain.entities.PaginatedUsers
import com.elitec.compralibre.domain.repository.UserRepo
import io.appwrite.ID
import io.appwrite.Query
import io.appwrite.exceptions.AppwriteException
import io.appwrite.models.Document
import io.appwrite.models.DocumentList
import io.appwrite.models.Users
import io.appwrite.services.Databases
import javax.inject.Inject

class UsersRepoImpl @Inject constructor(
    private val _database: Databases,
    private val _databaseInfo: AppWriteDataBaseInfo
): UserRepo {
    override suspend fun createUser(user: Users): Result<Any> {
        return try {
            val id = ID.unique()
            val userData = user.copy(uid = id)
            val document = _database.createDocument(
                databaseId = _databaseInfo.getDatabaseID(),
                collectionId = _databaseInfo.getUserCollectionID(),
                documentId = id, // Usa uid como ID del documento
                data = userData.toDocumentData()
            )
            Result.success(document.toUser().uid)
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun getUserById(documentId: String): Result<Users> {
        return try {
            val document = _database.getDocument(
                databaseId = _databaseInfo.getDatabaseID(),
                collectionId = _databaseInfo.getUserCollectionID(),
                documentId = documentId
            )
            Result.success(document.toUser())
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun getUsers(page: Int, pageSize: Int): Result<PaginatedUsers> {
        return try {
            val offset = page * pageSize
            val documentList = _database.listDocuments(
                databaseId = _databaseInfo.getDatabaseID(),
                collectionId = _databaseInfo.getUserCollectionID(),
                queries = listOf(
                    Query.limit(pageSize),
                    Query.offset(offset)
                )
            )
            Result.success(documentList.toPaginatedUsers(page, pageSize))
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun updateUser(documentId: String, user: Users): Result<Users> {
        return try {
            val document = _database.updateDocument(
                databaseId = _databaseInfo.getDatabaseID(),
                collectionId = _databaseInfo.getUserCollectionID(),
                documentId = documentId,
                data = user.toDocumentData()
            )
            Result.success(document.toUser())
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    override suspend fun deleteUser(documentId: String): Result<Unit> {
        return try {
            _database.deleteDocument(
                databaseId = _databaseInfo.getDatabaseID(),
                collectionId = _databaseInfo.getUserCollectionID(),
                documentId = documentId
            )
            Result.success(Unit)
        } catch (e: AppwriteException) {
            Result.failure(e)
        }
    }

    private fun Document<Map<String, Any>>.toUser(): Users {
        return Users.fromDocument(this)
    }

    private fun DocumentList<Map<String, Any>>.toPaginatedUsers(page: Int, pageSize: Int): PaginatedUsers {
        val users = this.documents.map { it.toUser() }
        return PaginatedUsers(
            users = users,
            total = users.size,
            page = page,
            pageSize = pageSize,
            hasNextPage = (page + 1) * pageSize < total
        )
    }
}