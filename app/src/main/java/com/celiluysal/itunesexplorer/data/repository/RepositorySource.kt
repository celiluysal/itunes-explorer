package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.Resource
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import kotlinx.coroutines.flow.Flow

interface RepositorySource {
    suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?,
    ): Flow<Resource<SearchResult>>
}