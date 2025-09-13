package com.example.gpt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GenderTranslatorApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
