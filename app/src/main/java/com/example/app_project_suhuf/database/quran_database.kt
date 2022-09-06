package com.example.app_project_suhuf.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quran_database")
data class quran_database(
    @PrimaryKey val  id: Int? = 0,
    @ColumnInfo(name="jozz")
    val juz: Int? = 0,
    @ColumnInfo(name = "sora")
    val numberSurah: Int? = 0,
    @ColumnInfo(name = "sora_name_en")
    val surahName: String? = "",
    @ColumnInfo(name = "sora_name_ar")
    val surahNameAr : String? = "",
    val page: Int? = 0,
    @ColumnInfo(name = "line_start")
    val lineStart: Int? = 0,
    @ColumnInfo(name = "line_end")
    val lineEnd: Int? = 0,
    @ColumnInfo(name = "aya_no")
    val noAyah: Int? = 0,
    @ColumnInfo(name = "aya_text")
    val textAyah: String? = "",
    @ColumnInfo(name = "aya_text_emlaey")
    val imlaText: String? = "",
    val translation: String? = "",
    val footnotes: String? = "",

)
