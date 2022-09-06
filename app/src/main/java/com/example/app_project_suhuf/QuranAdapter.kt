package com.example.app_project_suhuf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.app_project_suhuf.database.quran_database

class QuranAdapter(val quranList: List<quran_database>): RecyclerView.Adapter<QuranAdapter. QuranViewHolder>() {

    var itemClickListener: ((quran_database) -> Unit)? = null

    class QuranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(quran: quran_database) {
            textSurahName.text = "${quran.surahName} │ ${quran.surahNameAr}"
            textAyah.text = quran.textAyah
            textTranslation.text = "${quran.noAyah} » ${quran.translation}"
        }

        val textSurahName = itemView.findViewById<TextView>(R.id.text_surah_name)
        val textAyah = itemView.findViewById<TextView>(R.id.text_ayah_quran)
        val textTranslation = itemView.findViewById<TextView>(R.id.text_ayah_translation)
        val tombolBalik = itemView.findViewById<ImageView>(R.id.backbutton_surah)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quranreading, parent, false)
        return QuranViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuranViewHolder, position: Int) {
        val quran: quran_database = quranList[position]
        holder.tombolBalik.isVisible = quran.noAyah == 1
        holder.bindView(quran)
        holder.textSurahName.isVisible = quran.noAyah == 1
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke(quran)
        }
    }

    override fun getItemCount(): Int {
        return quranList.size
    }
}