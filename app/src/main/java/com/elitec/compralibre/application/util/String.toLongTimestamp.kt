package com.elitec.compralibre.application.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLongTimestamp(): Long {
    return try {
        Instant.parse(this).toEpochMilli()
    } catch (e: Exception) {
        System.currentTimeMillis()
    }
}