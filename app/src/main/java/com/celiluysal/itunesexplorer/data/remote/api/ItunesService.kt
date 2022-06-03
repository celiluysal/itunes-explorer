package com.celiluysal.itunesexplorer.data.remote.api

import com.celiluysal.itunesexplorer.data.model.responses.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {
    @GET("/search")
    suspend fun search(
        @Query("term") term: String?,
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?,
        @Query("entity") entity: String?,
    ): Response<SearchResult>
}