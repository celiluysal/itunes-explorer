package com.celiluysal.itunesexplorer.data.model.responses

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class SearchResult(
    @Json(name = "resultCount")
    val resultCount: Int? = null,
    @Json(name = "results")
    var results: List<MediaItem>? = null
): Parcelable