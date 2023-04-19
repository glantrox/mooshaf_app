package com.hamas.app_project_suhuf

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.SuperscriptSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate

import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import hamas.app_project_suhuf.R
import java.util.regex.Matcher
import java.util.regex.Pattern

class FootNotesFragment: BottomSheetDialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):
            View? = inflater.inflate(R.layout.bottomsheet_footnotes, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_footnotes)
        val textFootNotes = view.findViewById<TextView>(R.id.text_footnotes)


        val footnotes = arguments?.getString(EXTRA_FOOTNOTES)
        val surahName = arguments?.getString(EXTRA_SURAH_NAME)
        val surahNameAr = arguments?.getString(EXTRA_SURAH_NAME_AR)
        val ayahNumber = arguments?.getInt(EXTRA_AYAH_NUMBER)

        fun createFootnotesSuperscript(textView: TextView, translation: String) {

            when(SharedPreferences(requireContext()).theme) {
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

            val colorPrimary = textView.resources.getColor(R.color.mainRed)
            val sb = SpannableStringBuilder(translation)
            val p: Pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE)
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
                    SuperscriptSpan(),
                    m.start(),
                    m.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            textView.text = sb
        }

        toolbar.subtitle = "$surahNameAr"
        toolbar.title = "$surahName: $ayahNumber"



        if (footnotes.isNullOrBlank()) {
            dismiss()
        } else {
            textFootNotes.text = footnotes.toString().replace("\n", "\n\n\n")
            createFootnotesSuperscript(textFootNotes, footnotes.toString())


        }


        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.btn_back) {
                dismiss()
            }

            if (it.itemId == R.id.btn_copy_footnotes) {
               if (footnotes.isNullOrEmpty()) {
                   Toast.makeText(requireContext(),"Footnotes is Unavailable", Toast.LENGTH_SHORT).show()
               } else {
                   val shareIntent = Intent()
                   shareIntent.putExtra(Intent.EXTRA_TEXT, "$footnotes")
                   shareIntent.action = Intent.ACTION_SEND
                   shareIntent.type = "text/plain"
                   startActivity(Intent.createChooser(shareIntent, "Share To"))
               }
            }


            true
        }

    }

    companion object {
        const val EXTRA_FOOTNOTES = "foot_notes"
        const val EXTRA_SURAH_NAME = "surah_name"
        const val EXTRA_SURAH_NAME_AR = "surah_name_ar"
        const val EXTRA_AYAH_NUMBER = "ayah_number"

    }
}