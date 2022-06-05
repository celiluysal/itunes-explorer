package com.celiluysal.itunesexplorer.data.remote.source

import com.celiluysal.itunesexplorer.data.model.Resource
import com.celiluysal.itunesexplorer.data.model.responses.SearchResult

interface RemoteDataSource {
    suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?
    ): Resource<SearchResult>
}
