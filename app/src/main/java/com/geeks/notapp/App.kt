package com.geeks.notapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.room.Room
import com.geeks.notapp.data.db.AppDataBase
import com.geeks.notapp.utils.PreferenceaHelper
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.messaging.FirebaseMessaging

class App: Application() {

    companion object{
        var appDataBase: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceaHelper()
        sharedPreferences.unit(this)
        getInstance()
        createNotificationChannel()

        FirebaseMessaging.getInstance().subscribeToTopic("all")
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Log.d("FCM", "Successfully subscribed to notifications")
                }
            }
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

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("default", "Default Notifications", NotificationManager.IMPORTANCE_HIGH)
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }
}