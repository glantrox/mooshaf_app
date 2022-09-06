package com.example.app_project_suhuf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_project_suhuf.database.Surah

class  SurahAdapter(val surahList: List<Surah>): RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    var itemClickListener: ((Surah) -> Unit)? = null
    class SurahViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textSurahName = itemView.findViewById<TextView>(R.id.text_surah_name)
        val textSurahArabic = itemView.findViewById<TextView>(R.id.text_surah_arabic)
        val textSurahNumber = itemView.findViewById<TextView>(R.id.text_surah_number)
        val textTotalAyah = itemView.findViewById<TextView>(R.id.text_total_ayah)
        fun bindView(surah: Surah) {
            textSurahName.text = surah.surahName
            textSurahArabic.text = surah.surahNameArabic
            textSurahNumber.text = surah.surahNumber.toString()
            textTotalAyah.text = "${surah.totalAyah} Ayat"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surah, parent, false)
        return SurahViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        val surah: Surah = surahList[position]
        holder.bindView(surah)
        holder.itemView.setOnClickListener{
            itemClickListener?.invoke(surah)
        }


    }

    override fun getItemCount(): Int {
        return surahList.size
    }


}