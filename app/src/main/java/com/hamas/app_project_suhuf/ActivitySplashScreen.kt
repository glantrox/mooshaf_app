package com.hamas.app_project_suhuf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import hamas.app_project_suhuf.R


import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivitySplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@ActivitySplashScreen, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.bottom_up, R.anim.nothing)
        }
    }
}