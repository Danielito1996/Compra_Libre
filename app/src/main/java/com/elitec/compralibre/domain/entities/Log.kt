package com.elitec.compralibre.domain.entities

/**
 * A data model for holding log entries.
 */
data class Log(
    val date: String,
    val status: String,
    val method: String,
    val path: String,
    val response: String,
)