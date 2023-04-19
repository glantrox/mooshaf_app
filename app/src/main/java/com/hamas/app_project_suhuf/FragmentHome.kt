package com.hamas.app_project_suhuf


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider
import hamas.app_project_suhuf.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class FragmentHome: Fragment(R.layout.fragment_home) {


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    @SuppressLint("SetTextI18n")
    override fun onStart() {
            super.onStart()
        val textLastReadSurahName = view?.findViewById<TextView>(R.id.tv_lastreadSurah)
        val textContinueOrStart = view?.findViewById<TextView>(R.id.tv_continueOrStartRead)


        val surahName = SharedPreferences(requireContext()).isLRSurahNama
        val ayahNumber = SharedPreferences(requireContext()).isLRAyahNumber
        val ayahNumber2 = SharedPreferences(requireContext()).isLRAyahNumber.toFloat()
        val surahNumber = SharedPreferences(requireContext()).IsLRSurahNumber
        val currentTotalayah = SharedPreferences(requireContext()).isLRCurrentTotalAyah.toFloat()
        val progressBar = view?.findViewById<Slider>(R.id.progressbar_reading)
        val cardLastRead = view?.findViewById<MaterialCardView>(R.id.reading_progresscard)
        val progresstext = view?.findViewById<TextView>(R.id.progresstext_card)
        val tvDate = view?.findViewById<TextView>(R.id.tv_date)
        val df = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())


        tvDate?.text = df.format(Date())

        // Ayah Number dibagi Total Ayat di kali 100
//        val ayahNumberProgress =
//        SharedPreferences(requireContext()).isLRAyahNumber /
//                SharedPreferences(requireContext()).isLRCurrentTotalAyah
        val percentageLastread = ayahNumber2 / currentTotalayah * 100


//        lifecycleScope.launch{
//            Toast.makeText(requireContext(), "$percentageLastread % ", Toast.LENGTH_SHORT).show()
//            delay(70)
//            Toast.makeText(requireContext(), "Current Total Ayah $currentTotalayah  ", Toast.LENGTH_SHORT).show()
//            delay(70)
//            Toast.makeText(requireContext(), "Current Ayah Number $ayahNumber  ", Toast.LENGTH_SHORT).show()
//
//        }




        val gotoHadits = view?.findViewById<ImageView>(R.id.icon_haditsarbain)
        val gotoShalatSchedule = view?.findViewById<ImageView>(R.id.icon_sholatdashboard)
        val gotoKalender = view?.findViewById<ImageView>(R.id.icon_kalenderdashboard)
        val gotoMasjid = view?.findViewById<ImageView>(R.id.icon_kajiandashboard)
        val gotoDoaDoa = view?.findViewById<ImageView>(R.id.icon_doadoadashboard)



        gotoHadits?.setOnClickListener {
            Toast.makeText(requireContext(),"We're sorry this Feature is Coming Soon!", Toast.LENGTH_LONG).show() }
        gotoShalatSchedule?.setOnClickListener {
            Toast.makeText(requireContext(),"We're sorry this Feature is Coming Soon!", Toast.LENGTH_LONG).show() }
        gotoKalender?.setOnClickListener {
            Toast.makeText(requireContext(),"We're sorry this Feature is Coming Soon!", Toast.LENGTH_LONG).show() }
        gotoMasjid?.setOnClickListener {
           lifecycleScope.launch {
               Toast.makeText(requireContext(),"Looking for nearest Mosque..", Toast.LENGTH_LONG).show()
               delay(32)
               Toast.makeText(requireContext(),"Mosque Found!", Toast.LENGTH_SHORT).show()
           }

            val gmmIntentUri: Uri = Uri.parse("geo:0,0?q=Masjid")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)


        }
        gotoDoaDoa?.setOnClickListener {
            Toast.makeText(requireContext(),"We're sorry this Feature is Coming Soon!", Toast.LENGTH_LONG).show() }





        progressBar?.isEnabled = false
        progresstext?.text = "${percentageLastread.roundToInt()}%"
        progressBar?.setValue(percentageLastread.roundToInt().toFloat())
        if(ayahNumber2 == 100f) {
            SharedPreferences(requireContext()).isLRSurahNama.plus(1)
        }
        if(ayahNumber2.toInt() == 1) {
            progresstext?.text = "0%"
            progressBar?.setValue(0f)
        }



            cardLastRead?.setOnClickListener {
                val intent = Intent(requireContext(), QuranActivity::class.java)
                intent.putExtra(QuranActivity.SURAH_NUMBER, surahNumber)
                intent.putExtra(QuranActivity.AYAH_NUMBER, ayahNumber)
                intent.putExtra(QuranActivity.SURAH_NAME, surahName)
                startActivity(intent)
                getActivity()?.overridePendingTransition(R.anim.bottom_up, R.anim.nothing)
            }









        if(SharedPreferences(requireContext()).isLRAyahNumber == 1) {
            textContinueOrStart?.text = "Start Reading"
            textLastReadSurahName?.text = "Read starts from $surahName"
        } else {
            textContinueOrStart?.text = "Continue Reading"
            textLastReadSurahName?.text = "Last Read $surahName: $ayahNumber"
        }

//        textLastReadSurahName?.text = "Last Read $surahName: $ayahNumber"


        // Buttons
        val gotoQiblatActivity = view?.findViewById<ImageView>(R.id.icon_kiblatdashboard)
        gotoQiblatActivity?.setOnClickListener {
            val intent = Intent(requireContext(), QiblatActivity::class.java)
            startActivity(intent)
        }
    }
}