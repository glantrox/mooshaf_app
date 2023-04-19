package com.hamas.app_project_suhuf.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bookmark::class], version = 3)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun QuranDao(): BookmarkDao

    companion object {

        @Volatile private var INSTANCE: BookmarkDatabase? = null
        fun getInstance(context: Context): BookmarkDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): BookmarkDatabase {
            return  Room.databaseBuilder(context.applicationContext,BookmarkDatabase::class.java, "bookmark.db")
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}
