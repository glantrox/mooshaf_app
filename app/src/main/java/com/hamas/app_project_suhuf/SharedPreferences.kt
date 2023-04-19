package com.hamas.app_project_suhuf

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreferences(context: Context) {
    companion object {
        private const val TEXT_VISIBLE = "translation_text"
        private const val THEME_KEY = "tema_value"
        private  const val QARI_KEY = "QARI_VALUE"
        private const val ARAB_PREFERENCE = "arab_size"
        private const val TAJWEED_PREFERENCE = "arab_tajweed"
        private  const val TRANSLATION_PREFERENCE = "translation_size"
        private  const val BOOKMARK_PREFERENCE = "bookmark_iconed"

        const val KEY_SURAH_NAME = "surah_name"
        const val KEY_AYAH_NUMBER = "ayah_number"
        const val KEY_SURAH_NUMBER = "surah_number"
        const val KEY_TOTAL_AYAH = "total_ayah"
    }

    // Last Read: Total Ayah
    private val currentLRTotalAyah = PreferenceManager.getDefaultSharedPreferences(context)
    var isLRCurrentTotalAyah = currentLRTotalAyah.getInt(KEY_TOTAL_AYAH, 1)
    set(value) = currentLRTotalAyah.edit().putInt(KEY_TOTAL_AYAH, value).apply()

    // Last Read: Surah Name
    private val currentLRSurahName = PreferenceManager.getDefaultSharedPreferences(context)
    var isLRSurahNama = currentLRSurahName.getString(KEY_SURAH_NAME,"Al-Fatihah")
    set(value) = currentLRSurahName.edit().putString(KEY_SURAH_NAME, value).apply()

    // Last Read: Ayah Number
    private val currentLRAyahNumber = PreferenceManager.getDefaultSharedPreferences(context)
    var isLRAyahNumber = currentLRAyahNumber.getInt(KEY_AYAH_NUMBER, 1)
    set(value) = currentLRAyahNumber.edit().putInt(KEY_AYAH_NUMBER, value).apply()

    // Last Read: Surah Number
    private val currentLRSurahNumber = PreferenceManager.getDefaultSharedPreferences(context)
    var IsLRSurahNumber = currentLRSurahNumber.getInt(KEY_SURAH_NUMBER, 1)
    set(value) = currentLRSurahNumber.edit().putInt(KEY_SURAH_NUMBER, value).apply()



    //Text Visibility Preferences
    private val currentText = PreferenceManager.getDefaultSharedPreferences(context)
    var isTextVisible = currentText.getInt(TEXT_VISIBLE, 0)
    set(value) = currentText.edit().putInt(TEXT_VISIBLE, value).apply()

    //Night Mode State Preferences
    private  val currentTheme = PreferenceManager.getDefaultSharedPreferences(context)
    var theme = currentTheme.getBoolean(THEME_KEY, false)
    set(value) = currentTheme.edit().putBoolean(THEME_KEY, value).apply()

    //Arabic Font Size Preferences
    private val currentArabSize = PreferenceManager.getDefaultSharedPreferences(context)
    var isArabSize = currentArabSize.getInt(ARAB_PREFERENCE, 34f.toInt())
    set(value) = currentArabSize.edit().putInt(ARAB_PREFERENCE, value).apply()

    //Tajweed Color Preferences
    private val currentArabTajweed = PreferenceManager.getDefaultSharedPreferences(context)
    var isTajweedColorized = currentArabTajweed.getInt(TAJWEED_PREFERENCE, 0)
    set(value) = currentArabTajweed.edit().putInt(TAJWEED_PREFERENCE, value).apply()

    //Translation Font Size Preferences
    private val currentTranslationSize = PreferenceManager.getDefaultSharedPreferences(context)
    var isTranslationSize = currentTranslationSize.getInt(TRANSLATION_PREFERENCE, 23f.toInt())
    set(value) = currentTranslationSize.edit().putInt(TRANSLATION_PREFERENCE, value).apply()

    // Bookmark Preferences
    private  val currentBookmark = PreferenceManager.getDefaultSharedPreferences(context)
    var isBookmarked = currentBookmark.getInt(BOOKMARK_PREFERENCE, 0)
    set(value) = currentBookmark.edit().putInt(BOOKMARK_PREFERENCE, value).apply()

    //Qari Preferences
    private  val currentQari = PreferenceManager.getDefaultSharedPreferences(context)
    var isQari = currentQari.getInt(QARI_KEY, 0)
    set(value) = currentQari.edit().putInt(QARI_KEY, value).apply()







}