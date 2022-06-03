package com.celiluysal.itunesexplorer.data.model.responses


import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val results: List<MediaItem>? = null
)