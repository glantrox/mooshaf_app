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
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

import com.hamas.app_project_suhuf.database.quran_database
import com.hamas.app_project_suhuf.utils.QuranArabicUtils
import com.google.android.material.divider.MaterialDivider
import com.l4digital.fastscroll.FastScroller
import hamas.app_project_suhuf.R
import java.util.regex.Matcher
import java.util.regex.Pattern


class QuranAdapter(val quranList: List<quran_database>): RecyclerView.Adapter<QuranAdapter. QuranViewHolder>(), FastScroller.SectionIndexer {


    var playClickListener: ((quran_database) -> Unit)? = null
    var menuClickListener: ((quran_database) -> Unit)? = null
    var itemClickListener: ((quran_database) -> Unit)? = null
    var copyClickListener: ((quran_database) -> Unit)? = null
    var footnotesClickListener: ((quran_database) -> Unit)? = null
    var shareClickListener: ((quran_database) -> Unit)? = null
    var bookmarkClickListener: ((quran_database) -> Unit)? = null
//    var changeItemColor: ((Int) -> Color)? = null

    class QuranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(quran: quran_database) {

            //
            val ayahQuran = if (Build.VERSION.SDK_INT >= 13.0) {
                quran.textAyah
            } else {
                reverseAyahNumber(quran.textAyah.toString())
            }
            val context = itemView.context


             //Tajweed Color Setting : Colorized Arabic Text
            when(SharedPreferences(context).isTajweedColorized) {
                0 -> {
                    textAyah.text = ayahQuran
                }
                1 -> {
                    textAyah.text = QuranArabicUtils.getTajweed(context, ayahQuran)
                }
            }



            // Translation Bind View
            textTranslation.text = "${quran.translation}"


            // Translation Font Size Setting
            textTranslation.textSize = SharedPreferences(context).isTranslationSize.toFloat()

            // Arabic Font Size Setting
           textAyah.textSize = SharedPreferences(context).isArabSize.toFloat()








            textNoAyah.text = quran.noAyah.toString()

            // Trigger Function #FootnotesSuperScript
            createFootnotesSuperscript(textTranslation, quran.translation.toString())

        }



        // Function : Footnotes Superscript
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



        // Function : Reverse Nomor Ayat
        fun reverseAyahNumber(textAyah: String): String{
            val digits = mutableListOf<Char>()
            textAyah.forEach { char ->
                if (char.isDigit()){
                    digits.add(char)
                }
            }
            val reverseDigits = digits.reversed()
            return textAyah.replace(digits.joinToString("") , reverseDigits.joinToString(""))

        }


        //        val tombolMenuPopup = itemView.findViewById<MaterialCardView>(R.id.menu_button)
        val textNoAyah = itemView.findViewById<TextView>(R.id.text_no_ayah)
        val textAyah = itemView.findViewById<TextView>(R.id.text_ayah_quran)
        val textTranslation = itemView.findViewById<TextView>(R.id.text_ayah_translation)
        val btnCopy = itemView.findViewById<ImageView>(R.id.btn_copy_ayah)
        val btnShare = itemView.findViewById<ImageView>(R.id.btn_share_ayah)
        val btnPlay = itemView.findViewById<ImageView>(R.id.btn_play_ayah)
        val btnBookmark = itemView.findViewById<ImageView>(R.id.btn_bookmark_ayah)
        val textBasmallah = itemView.findViewById<TextView>(R.id.text_basmallah_quran)
        val dividerBasmallah = itemView.findViewById<MaterialDivider>(R.id.divider_basmallah)



    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuranViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quranreading, parent, false)
        return QuranViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: QuranViewHolder, position: Int) {
        val quran: quran_database = quranList[position]
        holder.bindView(quran)
        if(quran.numberSurah == 9) {
            holder.textBasmallah.isGone = true
            holder.dividerBasmallah.isGone = true
        } else {
            holder.textBasmallah.isVisible = quran.noAyah == 1
            holder.dividerBasmallah.isVisible = quran.noAyah == 1
        }

        if(quran.numberSurah == 1) {
            holder.textBasmallah.isVisible = quran.noAyah == 1
            holder.textBasmallah.text = "أَعُوْذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِِْ"
            holder.dividerBasmallah.isVisible = quran.noAyah == 1
        }





        holder.textTranslation.setOnClickListener {
            footnotesClickListener?.invoke(quran)
        }
        holder.btnPlay.setOnClickListener {
            playClickListener?.invoke(quran)
        }
        holder.btnShare.setOnClickListener {
            shareClickListener?.invoke(quran)
        }
        holder.btnCopy.setOnClickListener {
            copyClickListener?.invoke(quran)
        }
        holder.itemView.setOnClickListener{
            itemClickListener?.invoke(quran)
        }
        holder.btnBookmark.setOnClickListener {
            bookmarkClickListener?.invoke(quran)
        }

//        holder.textTranslation.isVisible = SharedPreferences(holder.itemView.context).isTextVisible
        when (SharedPreferences(holder.itemView.context).isTextVisible) {
            0 -> {
                holder.textTranslation.isVisible = true
            }
            1 -> {
                holder.textTranslation.isVisible = false
            }
        }






    }



    override fun getItemCount(): Int {
        return quranList.size
    }

    override fun getSectionText(position: Int): CharSequence {
        return "${quranList[position].noAyah}"
    }


}