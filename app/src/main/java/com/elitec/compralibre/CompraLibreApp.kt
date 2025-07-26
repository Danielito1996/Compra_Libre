package com.elitec.compralibre

import android.app.Application
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CompraLibreApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GoogleAuthProvider.create(credentials = GoogleAuthCredentials(serverId = "861734761304-6ls42ied1p1n4m6fv8rbke8q4q26ph7s.apps.googleusercontent.com"))
    }
}