package com.elitec.compralibre.domain.caseUser.auth.google

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT

fun decodeJWT(idToken: String): DecodedJWT { //Obtener el Id de ckient unico para el sistem a
    return JWT.decode(idToken)
} //Evita ataques Man-In-The-Meadle
