package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.local.ItunesDatabase
import com.celiluysal.itunesexplorer.data.local.dao.MediaItemDao
import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.data.remote.source.RemoteDataSourceFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepositoryImpl @Inject constructor(
    private val remoteData: RemoteDataSourceFactory,
    private val itunesDatabase: ItunesDatabase,
    private val ioDispatcher: CoroutineContext
) : Repository {

    override suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?
    ): Flow<Resource<SearchResult>> {
        return flow {
            emit(remoteData.search(term, limit, offset, entity))
        }.flowOn(ioDispatcher)
    }

    override suspend fun addFavoriteMediaItem(mediaItem: MediaItem) {
        itunesDatabase.getMediaItemDao().addMediaItem(mediaItem)
    }

    override suspend fun deleteFavoriteMediaItem(mediaItem: MediaItem) {
        mediaItem.trackId?.let { itunesDatabase.getMediaItemDao().deleteMediaItem(it) }
    }

    override suspend fun getAllFavoriteMediaItems(): Flow<List<MediaItem>> {
        return itunesDatabase.getMediaItemDao().getAllMediaItems()
    }

    override suspend fun isMediaItemFavorite(mediaItem: MediaItem): Boolean {
        return mediaItem.trackId?.let { itunesDatabase.getMediaItemDao().isMediaItemExist(it) } ?: false
    }

}