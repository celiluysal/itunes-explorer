package com.celiluysal.itunesexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMediaItem(mediaItem: MediaItem)

    @Query("DELETE FROM ${MediaItem.TABLE_NAME} WHERE trackId = :trackId")
    suspend fun deleteMediaItem(trackId: Int)

    @Query("SELECT * FROM ${MediaItem.TABLE_NAME}")
    fun getAllMediaItems(): Flow<List<MediaItem>>

    @Query("SELECT EXISTS(SELECT * FROM ${MediaItem.TABLE_NAME} WHERE trackId = :trackId)")
    suspend fun isMediaItemExist(trackId: Int): Boolean

}