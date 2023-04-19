package com.hamas.app_project_suhuf.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface QuranDao {
 @Query("SELECT * FROM surah")
 suspend fun getSurahList(): List<Surah>

 @Query("SELECT * FROM juz")
 suspend fun  getJuzList(): List<Juz>

 @Query("SELECT * FROM page")
 suspend fun getPageList(): List<Page>

 @Query("SELECT * FROM quran_database WHERE sora = :surahNumber ")
 suspend fun getAyahFromSurah(surahNumber: Int): List<quran_database>

 @Query("SELECT * FROM quran_database WHERE jozz = :juzNumber ")
 suspend fun getAyahFromJuz(juzNumber: Int): List<quran_database>

 @Query("SELECT * FROM quran_database WHERE page = :pageNumber ")
 suspend fun getAyahFromPage(pageNumber: Int): List<quran_database>

 @Query("SELECT * FROM quran_database WHERE translation LIKE :search ")
 suspend fun getTranslationSearch(search: String): List<quran_database>

 @Query("SELECT * FROM surah WHERE sora_name_en LIKE :search ")
 suspend fun getSurahSearch(search: String): List<Surah>

 @Query("SELECT total_ayah, sora, sora_name_ar, sora_name_en  FROM surah WHERE sora = :currentSurah ORDER BY sora")
 suspend fun getTotalAyah(currentSurah: Int): List<Surah>




}