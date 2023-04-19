package com.hamas.app_project_suhuf.database

import androidx.room.DatabaseView
import androidx.room.ColumnInfo

@DatabaseView("SELECT sora, sora_name_ar, sora_name_en, COUNT(aya_no) AS total_aya FROM quran_database")
data class Page(
    @ColumnInfo(name = "sora")
    val surahNumber: Int,
    @ColumnInfo(name = "sora_name_ar")
    val surahNameArabic: String,
    @ColumnInfo(name = "sora_name_en")
    val surahName: String,
    @ColumnInfo(name = "total_aya")
    val totalAyah: Int
)
