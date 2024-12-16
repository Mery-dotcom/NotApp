package com.geeks.notapp.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceaHelper {

    private lateinit var sharedPref: SharedPreferences

    fun unit(context: Context){
        sharedPref = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    var text: String?
        get() = sharedPref.getString("text", "")
        set(value) = sharedPref.edit().putString("text", value)!!.apply()

    var isOnBoardShown: Boolean
        get() = sharedPref.getBoolean("board", false)
        set(value) = sharedPref.edit().putBoolean("board", value).apply()
}