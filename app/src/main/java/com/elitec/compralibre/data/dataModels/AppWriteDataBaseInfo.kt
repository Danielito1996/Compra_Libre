package com.elitec.compralibre.data.dataModels

class AppWriteDataBaseInfo {
    private val databaseId = "687c35830033097283a5"
    private val userCollectionId = "users_collection_id"

    fun getDatabaseID(): String = databaseId
    fun getUserCollectionID(): String = userCollectionId
}