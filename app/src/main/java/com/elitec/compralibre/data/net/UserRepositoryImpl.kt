package com.elitec.pregonero.data.net

import com.elitec.compralibre.domain.entities.User
import com.elitec.compralibre.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
): UserRepository {
    override suspend fun saveUser(user: User): Result<Unit> {
        TODO()
    }
}