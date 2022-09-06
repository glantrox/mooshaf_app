package com.example.app_project_suhuf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_quran.data.QuranDataBase
import kotlinx.coroutines.launch

class FragmentQuran: Fragment(R.layout.fragment_quran) {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview_surah)
        val database = QuranDataBase.getInstance(requireContext())

        lifecycleScope.launch {
            val surahList = database.QuranDao().getSurahList()
            val adapter = SurahAdapter(surahList)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            adapter.itemClickListener = {
                val intent = Intent(requireContext(), QuranActivity::class.java)
                intent.putExtra("surah", it.surahNumber)
                startActivity(intent)

                

            }
        }




    }



}

