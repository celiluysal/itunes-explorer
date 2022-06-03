package com.celiluysal.itunesexplorer.data.model.responses

import com.google.gson.annotations.SerializedName

data class MediaItem(
    @SerializedName("wrapperType")
    val wrapperType: String? = null,
    @SerializedName("kind")
    val kind: String? = null,
    @SerializedName("artistId")
    val artistId: Int? = null,
    @SerializedName("collectionId")
    val collectionId: Int? = null,
    @SerializedName("trackId")
    val trackId: Int? = null,
    @SerializedName("artistName")
    val artistName: String? = null,
    @SerializedName("collectionName")
    val collectionName: String? = null,
    @SerializedName("trackName")
    val trackName: String? = null,
    @SerializedName("collectionCensoredName")
    val collectionCensoredName: String? = null,
    @SerializedName("trackCensoredName")
    val trackCensoredName: String? = null,
    @SerializedName("artistViewUrl")
    val artistViewUrl: String? = null,
    @SerializedName("collectionViewUrl")
    val collectionViewUrl: String? = null,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String? = null,
    @SerializedName("previewUrl")
    val previewUrl: String? = null,
    @SerializedName("artworkUrl30")
    val artworkUrl30: String? = null,
    @SerializedName("artworkUrl60")
    val artworkUrl60: String? = null,
    @SerializedName("artworkUrl100")
    val artworkUrl100: String? = null,
    @SerializedName("collectionPrice")
    val collectionPrice: Double? = null,
    @SerializedName("trackPrice")
    val trackPrice: Double? = null,
    @SerializedName("releaseDate")
    val releaseDate: String? = null,
    @SerializedName("collectionExplicitness")
    val collectionExplicitness: String? = null,
    @SerializedName("trackExplicitness")
    val trackExplicitness: String? = null,
    @SerializedName("discCount")
    val discCount: Int? = null,
    @SerializedName("discNumber")
    val discNumber: Int? = null,
    @SerializedName("trackCount")
    val trackCount: Int? = null,
    @SerializedName("trackNumber")
    val trackNumber: Int? = null,
    @SerializedName("trackTimeMillis")
    val trackTimeMillis: Int? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("currency")
    val currency: String? = null,
    @SerializedName("primaryGenreName")
    val primaryGenreName: String? = null,
    @SerializedName("isStreamable")
    val isStreamable: Boolean? = null,
    @SerializedName("contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @SerializedName("collectionArtistName")
    val collectionArtistName: String? = null,
    @SerializedName("collectionArtistId")
    val collectionArtistId: Int? = null,
    @SerializedName("collectionArtistViewUrl")
    val collectionArtistViewUrl: String? = null,
    @SerializedName("shortDescription")
    val shortDescription: String? = null,
    @SerializedName("longDescription")
    val longDescription: String? = null,
) {
    companion object {
        const val TABLE_NAME = "media_items"
    }
}