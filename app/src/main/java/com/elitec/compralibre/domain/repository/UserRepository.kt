package com.elitec.compralibre.domain.repository

import com.elitec.compralibre.domain.entities.User

interface UserRepository {
    suspend fun saveUser(user: User): Result<Unit>
}