package com.geeks.notapp.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.geeks.notapp.R
import com.geeks.notapp.utils.PreferenceaHelper

class MainActivity : AppCompatActivity() {

    private lateinit var prefHelper: PreferenceaHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefHelper = PreferenceaHelper()
        prefHelper.unit(this)

        if (!prefHelper.isOnBoardShown){
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
            navHostFragment.navController.navigate(R.id.onBoardFragment)
            prefHelper.isOnBoardShown = true
        }
    }
}