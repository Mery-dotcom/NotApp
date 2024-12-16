package com.geeks.notapp

import android.app.Application
import com.geeks.notapp.utils.PreferenceaHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceaHelper()
        sharedPreferences.unit(this)
    }
}