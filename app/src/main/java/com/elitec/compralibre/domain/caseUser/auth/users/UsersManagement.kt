package com.elitec.compralibre.domain.caseUser.auth.users

import com.elitec.compralibre.domain.entities.User
import com.elitec.compralibre.domain.repository.UserRepo
import com.elitec.compralibre.domain.repository.UserRepository
import io.appwrite.models.Users
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class UsersManagement @Inject constructor(
    private val userRepository: UserRepository,
    private val userRepo: UserRepo
) {
    private var _userList = MutableStateFlow<List<Users>>(listOf())
    val userList get() = _userList.asStateFlow()

    private var currentPage = 1
    private val pageSize = 10

    suspend operator fun invoke(user: User): Result<Unit> {
        return userRepository.saveUser(user)
    }

    suspend fun registerUser(user: Users): Result<Any> {
        return userRepo.createUser(user)
    }

    suspend fun actualizeListOfUsers(): Result<Unit> {
        return try {
            val result = userRepo.getUsers(page = currentPage, pageSize = pageSize)
            result.fold(
                onSuccess = { paginatedUsers ->
                    _userList.value = paginatedUsers.users // Reemplaza la lista para evitar duplicaciones
                    if (paginatedUsers.hasNextPage) {
                        currentPage++ // Incrementa la página para la próxima llamada
                    }
                    Result.success(Unit)
                },
                onFailure = { error ->
                    Result.failure(Exception("No se ha podido actualizar la lista de usuarios: ${error.message}"))
                }
            )
        } catch (e: Exception) {
            Result.failure(Exception("Error inesperado al actualizar la lista de usuarios: ${e.message}"))
        }
    }

    suspend fun deleteUser(userId: String): Result<Unit> {
        return userRepo.deleteUser(userId).onSuccess {
            // Actualiza la lista eliminando el usuario
            _userList.value = _userList.value.filter { it.uid != userId }
        }
    }

    suspend fun updateUser(user: Users): Result<Unit> {
        val response = userRepo.updateUser(user.uid, user)
        return if(response.isSuccess) {
            val data = response.getOrThrow()
            _userList.value = _userList.value.map { if (it.uid == user.uid) data else it }
            Result.success(Unit)
        } else {
            Result.failure(Exception("No se ha podido eliminar el usuarios"))
        }
    }

    // Método para reiniciar la paginación
    fun resetPagination() {
        currentPage = 0
        _userList.value = emptyList()
    }
}