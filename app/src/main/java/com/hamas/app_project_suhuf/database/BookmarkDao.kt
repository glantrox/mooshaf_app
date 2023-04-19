package com.hamas.app_project_suhuf.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("DELETE FROM bookmark ")
    suspend fun deleteAllBookmark()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmarkList: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmarkList: Bookmark)

    @Query("SELECT * FROM bookmark ORDER BY sora_name_en ASC")
    fun getBookmarks(): Flow<List<Bookmark>>
}