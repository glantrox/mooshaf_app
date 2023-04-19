package com.hamas.app_project_suhuf

import android.annotation.SuppressLint
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.SuperscriptSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.quran_database
import hamas.app_project_suhuf.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class SearchAdapter(val quranList: List<quran_database>): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var copyClickListener: ((quran_database) -> Unit)? = null
    var shareClickListener: ((quran_database) -> Unit)? = null
    var footnotesClickListener: ((quran_database) -> Unit)? = null
    var itemClickListener: ((quran_database) -> Unit)? = null

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(quran: quran_database) {


            if (Build.VERSION.SDK_INT >= 13.0) {
                textAyah.text = "${quran.textAyah}"
            } else {
                textAyah.text = reverseAyahNumber(quran.textAyah.toString())
            }
            textTranslation.text = "${quran.translation}\n(${quran.surahName}:${quran.noAyah}) "
            if (Build.VERSION.SDK_INT >= 13.0) {
                textNoAyah.text = "${quran.surahName}: ${quran.noAyah}"
            } else {
                textNoAyah.text = reverseAyahNumber(quran.noAyah.toString())
            }

            createFootnotesSuperscript(textTranslation, quran.translation.toString())



        }


        val textNoAyah = itemView.findViewById<TextView>(R.id.text_no_ayahSearch)
        val textAyah = itemView.findViewById<TextView>(R.id.text_ayah_quranSearch)
        val textTranslation = itemView.findViewById<TextView>(R.id.text_ayah_translationSearch)
        val btnCopy = itemView.findViewById<ImageView>(R.id.btn_copy_ayahSearch)
        val btnShare = itemView.findViewById<ImageView>(R.id.btn_share_ayahSearch)



        fun createTranslationSpannableKeyword(textView: TextView, translation: String) {

            val colorPrimary = textView.resources.getColor(R.color.mainRed)
            val sb = SpannableStringBuilder(translation)
            val p: Pattern = Pattern.compile("${textView.text}", Pattern.CASE_INSENSITIVE)
            val m: Matcher = p.matcher(translation)
            while (m.find()) {
                val clickableSpan = object  : ClickableSpan() {
                    override fun updateDrawState(textPaint: TextPaint) {
                        textPaint.color = colorPrimary
                        textPaint.textSize = 38f

                    }

                    override fun onClick(p0: View) {

                    }
                }
                sb.setSpan(clickableSpan, m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                sb.setSpan(
                    SpannableStringBuilder(),
                    m.start(),
                    m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            textView.text = sb
        }
        fun createFootnotesSuperscript(textView: TextView, translation: String) {

            val colorPrimary = textView.resources.getColor(R.color.mainRed)
            val sb = SpannableStringBuilder(translation)
            val p: Pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE)
            val m: Matcher = p.matcher(translation)
            while (m.find()) {
                val clickableSpan = object  : ClickableSpan() {
                    override fun updateDrawState(textPaint: TextPaint) {
                        textPaint.color = colorPrimary
                        textPaint.textSize = 32f
                    }

                    override fun onClick(p0: View) {

                    }
                }
                sb.setSpan(clickableSpan, m.start(), m.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE)
                sb.setSpan(
                    SuperscriptSpan(),
                    m.start(),
                    m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            textView.text = sb
        }

        fun reverseAyahNumber(textAyah: String): String {
            val digits = mutableListOf<Char>()
            textAyah.forEach { char ->
                if (char.isDigit()) {
                    digits.add(char)
                }
            }
            val reverseDigits = digits.reversed()
            return textAyah.replace(digits.joinToString(""), reverseDigits.joinToString(""))
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quransearch, parent, false)
            return SearchAdapter.SearchViewHolder(view)
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val quran: quran_database = quranList[position]
            holder.bindView(quran)
            holder.btnCopy.setOnClickListener {
                copyClickListener?.invoke(quran)
            }
            holder.btnShare.setOnClickListener {
                shareClickListener?.invoke(quran)
            }
            holder.textTranslation.setOnClickListener {
                footnotesClickListener?.invoke(quran)
            }
            holder.itemView.setOnClickListener {
                itemClickListener?.invoke(quran)
            }
        }

        override fun getItemCount(): Int {
            return quranList.size
        }

        companion object {
            const val KEY_SEARCH = "search_result"
        }

    }