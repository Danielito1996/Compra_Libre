package com.elitec.compralibre.domain.entities

import com.elitec.compralibre.domain.entities.typeOf.Roles

data class User(
    val uid: String,
    val email: String?,
    val displayName: String?,
    val photoUrl: String?,
    val role: String? = Roles.CUSTOM_USER.name,
    val createdAt: Long = System.currentTimeMillis()
)
