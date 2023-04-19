package com.hamas.app_project_suhuf


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.ContextUtils.getActivity
import hamas.app_project_suhuf.R

private lateinit var appBarConfiguration: AppBarConfiguration

private lateinit var bottomNavigationView: BottomNavigationView


class MainActivity : AppCompatActivity(R.layout.activity_main) {


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getActivity(this@MainActivity)?.let {
            getActivity(this)?.window?.
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }

        when(SharedPreferences(this@MainActivity).theme) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }



        val navController = findNavController(R.id.nav_host_fragment_container)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home ,
                R.id.navigation_quran ,
                R.id.navigation_settings
            )
        )







        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setupWithNavController(navController)



    }


}