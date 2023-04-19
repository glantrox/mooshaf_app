package com.hamas.app_project_suhuf

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.quran_database
import com.hamas.app_quran.data.QuranDataBase
import hamas.app_project_suhuf.R
import kotlinx.coroutines.launch

class SearchActivity2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        when(SharedPreferences(this@SearchActivity2).theme) {
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

        val btnBack = findViewById<ImageView>(R.id.backbutton_surah3Search)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewSearch)
        val database = QuranDataBase.getInstance(this)
        val search = findViewById<EditText>(R.id.search_bar)

        fun copyTextToClipboard(quran : quran_database) {
            val textToCopy = ("${quran.textAyah} \n\n${quran.translation} \n(QS ${quran.surahName}:${quran.noAyah}) ")
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", textToCopy)
            clipboardManager.setPrimaryClip(clipData)

            Toast.makeText(this, "Surah ${quran.surahName}:${quran.noAyah} copied to Clipboard! ", Toast.LENGTH_LONG).show()
        }

        btnBack.setOnClickListener{
            super.onBackPressed()
            finish()
        }



        fun setQuranAdapter(quranList: List<quran_database>) {

            lifecycleScope.launch {
                val adapter = SearchAdapter(quranList)
                recyclerView.adapter = adapter



                adapter.footnotesClickListener = {
                    val footNotesFragment = FootNotesFragment().apply {
                        arguments = bundleOf(
                            FootNotesFragment.EXTRA_FOOTNOTES      to  it.footnotes,
                            FootNotesFragment.EXTRA_SURAH_NAME_AR  to  it.surahNameAr,
                            FootNotesFragment.EXTRA_SURAH_NAME     to  it.surahName,
                            FootNotesFragment.EXTRA_AYAH_NUMBER    to  it.noAyah


                        )
                    }
                    footNotesFragment.show(supportFragmentManager, "footnotes")

                }

                adapter.itemClickListener = {
                    val intent = Intent(this@SearchActivity2, QuranActivity::class.java)
                    intent.putExtra("ayah", it.noAyah)
                    intent.putExtra("surah", it.numberSurah)
                    startActivity(intent)
                }

                adapter.copyClickListener = {
                    copyTextToClipboard(it)
                }
                adapter.shareClickListener = {
                    val shareIntent = Intent()
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${it.textAyah} \n\n${it.translation} \n(QS ${it.surahName}:${it.noAyah}) ")
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type = "text/plain"
                    startActivity(Intent.createChooser(shareIntent, "Share To"))
                }
            }
        }


        search.setOnEditorActionListener { textView, id, keyEvent ->
            //ketika user menekan submit
            if (id == EditorInfo.IME_ACTION_DONE){
                val query = "%${textView.text}%"
                lifecycleScope.launch {
                    val quranSearchList = database.QuranDao().getTranslationSearch(query)
                    setQuranAdapter(quranSearchList)
                }

            }
            false
        }









    }


}