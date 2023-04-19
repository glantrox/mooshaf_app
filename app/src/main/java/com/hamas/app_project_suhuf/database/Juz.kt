package com.hamas.app_project_suhuf.database

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView("SELECT jozz,sora_name_en,aya_no FROM quran_database GROUP BY jozz")
data class Juz(
    @ColumnInfo(name = "jozz")
    val juzNumber: Int,
    @ColumnInfo(name = "sora_name_en")
    val surahName: String,
    @ColumnInfo(name = "aya_no")
    val noAyah: Int

)
