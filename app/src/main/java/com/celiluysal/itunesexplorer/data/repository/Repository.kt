package com.celiluysal.itunesexplorer.data.repository

import com.celiluysal.itunesexplorer.data.remote.api.ItunesService

class Repository(private val apiService: ItunesService) {
    suspend fun search(
        term: String?,
        limit: Int?,
        offset: Int?,
        entity: String?,
    ) = apiService.search(term, limit, offset, entity)
}