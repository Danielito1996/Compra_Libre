package com.elitec.compralibre.domain.entities

import io.appwrite.models.Users

data class PaginatedUsers(
    val users: List<Users>,
    val total: Int,
    val page: Int,
    val pageSize: Int,
    val hasNextPage: Boolean
)
