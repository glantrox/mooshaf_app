package com.hamas.app_project_suhuf

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

import com.google.android.material.internal.ContextUtils
import com.just.agentweb.AgentWeb
import hamas.app_project_suhuf.R


class QiblatActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        ContextUtils.getActivity(this@QiblatActivity)?.let {
            ContextUtils.getActivity(this)?.window?.
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qiblat)

        val rootLayout = findViewById<RelativeLayout>(R.id.webViewQibla)
        AgentWeb.with(this)
            .setAgentWebParent((rootLayout), LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .createAgentWeb()
            .ready()
            .go("https://qiblafinder.withgoogle.com/intl/id/")



    }
}