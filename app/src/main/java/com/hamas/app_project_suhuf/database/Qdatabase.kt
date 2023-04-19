package com.hamas.app_quran.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hamas.app_project_suhuf.database.*
import hamas.app_project_suhuf.R


@Database(version = 1,
    entities = [quran_database::class],
    views = [Surah::class, Juz::class, Page::class])
abstract class QuranDataBase: RoomDatabase() {
    abstract fun QuranDao(): QuranDao


    companion object{
        @Volatile
        private var INSTANCE: QuranDataBase? = null
        fun getInstance(context: Context): QuranDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context,
                    QuranDataBase::class.java,
                    "database_quransuhuf.db"
                ).createFromInputStream{
                    context.resources.openRawResource(R.raw.database_quransuhuf)
                }.build()
            }
        }
    }
}