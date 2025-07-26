package com.elitec.compralibre.presentantion.navigation.home

import kotlinx.serialization.Serializable

interface HomeInternalScreen

@Serializable
data class Home(
    val userId: String
): HomeInternalScreen

@Serializable
data class Search(
    val userId: String
): HomeInternalScreen

@Serializable
data class Create(
    val userId: String
): HomeInternalScreen

@Serializable
data class Chat(
    val userId: String
): HomeInternalScreen

@Serializable
data class Account(
    val userId: String
): HomeInternalScreen