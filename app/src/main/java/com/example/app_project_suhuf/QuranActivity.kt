package com.example.app_project_suhuf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quran.data.QuranDataBase
import kotlinx.coroutines.launch

class QuranActivity : AppCompatActivity(R.layout.activity_quran) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val database = QuranDataBase.getInstance(this)

        val surahNumber: Int = intent.getIntExtra(SURAH_NUMBER, 1)

        lifecycleScope.launch {
            val quranList = database.QuranDao().getAyahFromSurah(surahNumber)
            val adapter = QuranAdapter(quranList)
            recyclerView.adapter = adapter


        }
    }

    companion object{
        const val SURAH_NUMBER = "surah"
    }

}