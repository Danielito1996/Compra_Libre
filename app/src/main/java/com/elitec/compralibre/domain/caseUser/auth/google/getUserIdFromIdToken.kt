package com.elitec.compralibre.domain.caseUser.auth.google

fun getUserIdFromIdToken(idToken: String): String {
    val decodedToken = decodeJWT(idToken)
    return decodedToken.getClaim("sub").asString() // Obtén el campo "sub"
}