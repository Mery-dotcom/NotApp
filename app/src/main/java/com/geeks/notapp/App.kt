package com.geeks.notapp

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.geeks.notapp.data.db.AppDataBase
import com.geeks.notapp.utils.PreferenceaHelper
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize

class App: Application() {

    companion object{
        var appDataBase: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceaHelper()
        sharedPreferences.unit(this)
        getInstance()
    }

    private fun getInstance(): AppDataBase? {
        if (appDataBase == null){
            appDataBase = applicationContext?.let { context: Context ->
                Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDataBase
    }
}