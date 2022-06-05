package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.data.remote.source.RemoteDataSourceFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RepositoryImpl @Inject constructor(private val remoteData: RemoteDataSourceFactory, private val ioDispatcher: CoroutineContext): Repository {

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

}