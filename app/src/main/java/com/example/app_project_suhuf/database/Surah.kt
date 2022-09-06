package com.example.app_project_suhuf.database

import androidx.room.DatabaseView
import androidx.room.ColumnInfo

@DatabaseView("SELECT sora, sora_name_ar, sora_name_en, COUNT(aya_no) AS total_ayah FROM quran_database GROUP BY sora")

data class Surah(
    @ColumnInfo(name = "sora")
    val surahNumber: Int,
    @ColumnInfo(name = "sora_name_ar")
    val surahNameArabic: String,
    @ColumnInfo(name = "sora_name_en")
    val surahName: String,
    @ColumnInfo(name = "total_ayah")
    val totalAyah: Int

)
