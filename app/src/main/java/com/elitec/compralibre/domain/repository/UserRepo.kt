package com.elitec.compralibre.domain.repository

import com.elitec.compralibre.domain.entities.PaginatedUsers
import io.appwrite.models.Users

interface UserRepo {
    suspend fun createUser(user: Users): Result<Any>
    suspend fun getUserById(documentId: String): Result<Users>
    suspend fun getUsers(page: Int, pageSize: Int): Result<PaginatedUsers>
    suspend fun updateUser(documentId: String, user: Users): Result<Users>
    suspend fun deleteUser(documentId: String): Result<Unit>
}