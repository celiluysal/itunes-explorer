package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.Resource
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import com.celiluysal.itunesexplorer.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Repository @Inject constructor(private val remoteData: RemoteData, private val ioDispatcher: CoroutineContext): RepositorySource {

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