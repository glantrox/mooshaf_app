package com.hamas.app_project_suhuf.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SurahViewmodel: ViewModel() {
    private val liveData = MutableLiveData<List<Int>>()
    fun settotalAyah(surahList: List<Surah>) {
        val totalAyah = mutableListOf<Int>()
        surahList.forEach{
            totalAyah.add(it.totalAyah)
        }
        liveData.value = totalAyah
    }

    fun getTotalAyah() = liveData
}