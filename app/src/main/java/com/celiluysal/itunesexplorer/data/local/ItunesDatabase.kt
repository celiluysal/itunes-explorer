package com.celiluysal.itunesexplorer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.celiluysal.itunesexplorer.data.local.dao.MediaItemDao
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem

@Database(entities = [MediaItem::class], version = 1, exportSchema = false)
abstract class ItunesDatabase : RoomDatabase() {

    abstract fun getMediaItemDao(): MediaItemDao

    companion object {
        private const val DB_NAME = "itunes_database"

        @Volatile
        private var instance: ItunesDatabase? = null

        fun getDatabase(context: Context): ItunesDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, ItunesDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}