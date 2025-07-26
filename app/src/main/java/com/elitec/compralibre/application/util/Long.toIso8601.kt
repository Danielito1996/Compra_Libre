package com.elitec.compralibre.application.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
fun Long.toIso8601(): String {
    return Instant.ofEpochMilli(this).toString() // Ejemplo: "2025-07-20T03:35:00.000Z"
}