package com.hamas.app_project_suhuf

import android.util.Log
import androidx.recyclerview.widget.RecyclerView

open class CustomScrollListener : RecyclerView.OnScrollListener() {
    fun onScrollStateChanged(newState: Int, recyclerView: RecyclerView?) {
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE      ->  println("The RecyclerView is not scrolling")
            RecyclerView.SCROLL_STATE_DRAGGING  ->  println("Scrolling now")
            RecyclerView.SCROLL_STATE_SETTLING  ->  println("Scroll Settling")
        }
    }

    fun onScrolled( dx: Int, dy: Int,recyclerView: RecyclerView?) {
        if (dx > 0) {
            Log.d("Scrolled Right","scroll_right")
        } else if (dx < 0) {
            Log.d("Scrolled Left","scroll_left")
        } else {
            Log.d("No Horizontal Scrolled", "scroll_negative_horizontal")
        }
        if (dy > 0) {
            Log.d("Scrolled Downwards", "scroll_downwards")
        } else if (dy < 0) {
            Log.d("Scrolled Upwards", "scroll_upwards")
        } else {
            Log.d("No Vertical Scrolled","scroll_negative_vertical")
        }
    }
}