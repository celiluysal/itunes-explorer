package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?,
    ): Flow<Resource<SearchResult>>

    suspend fun addFavoriteMediaItem(mediaItem: MediaItem)

    suspend fun deleteFavoriteMediaItem(mediaItem: MediaItem)

    suspend fun getAllFavoriteMediaItems(): Flow<List<MediaItem>>

    suspend fun isMediaItemFavorite(mediaItem: MediaItem): Boolean

}