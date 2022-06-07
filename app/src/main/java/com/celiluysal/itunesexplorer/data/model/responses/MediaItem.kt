package com.celiluysal.itunesexplorer.data.model.responses

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.celiluysal.itunesexplorer.data.model.responses.MediaItem.Companion.TABLE_NAME
import com.celiluysal.itunesexplorer.extensions.DATE_DOT_TEXT_FORMAT
import com.celiluysal.itunesexplorer.extensions.DATE_TIME_FORMAT
import com.celiluysal.itunesexplorer.extensions.toDate
import com.celiluysal.itunesexplorer.extensions.toString
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
@Entity(tableName = TABLE_NAME)
data class MediaItem(
    @PrimaryKey
    @Json(name = "trackId")
    val trackId: Int? = null,
    @Json(name = "wrapperType")
    val wrapperType: String? = null,
    @Json(name = "kind")
    val kind: String? = null,
    @Json(name = "artistId")
    val artistId: Int? = null,
    @Json(name = "collectionId")
    val collectionId: Int? = null,
    @Json(name = "artistName")
    val artistName: String? = null,
    @Json(name = "collectionName")
    val collectionName: String? = null,
    @Json(name = "trackName")
    val trackName: String? = null,
    @Json(name = "collectionCensoredName")
    val collectionCensoredName: String? = null,
    @Json(name = "trackCensoredName")
    val trackCensoredName: String? = null,
    @Json(name = "artistViewUrl")
    val artistViewUrl: String? = null,
    @Json(name = "collectionViewUrl")
    val collectionViewUrl: String? = null,
    @Json(name = "trackViewUrl")
    val trackViewUrl: String? = null,
    @Json(name = "previewUrl")
    val previewUrl: String? = null,
    @Json(name = "artworkUrl30")
    val artworkUrl30: String? = null,
    @Json(name = "artworkUrl60")
    val artworkUrl60: String? = null,
    @Json(name = "artworkUrl100")
    val artworkUrl100: String? = null,
    @Json(name = "collectionPrice")
    val collectionPrice: Double? = null,
    @Json(name = "trackPrice")
    val trackPrice: Double? = null,
    @Json(name = "price")
    val price: Double? = null,
    @Json(name = "releaseDate")
    val releaseDate: String? = null,
    @Json(name = "collectionExplicitness")
    val collectionExplicitness: String? = null,
    @Json(name = "trackExplicitness")
    val trackExplicitness: String? = null,
    @Json(name = "discCount")
    val discCount: Int? = null,
    @Json(name = "discNumber")
    val discNumber: Int? = null,
    @Json(name = "trackCount")
    val trackCount: Int? = null,
    @Json(name = "trackNumber")
    val trackNumber: Int? = null,
    @Json(name = "trackTimeMillis")
    val trackTimeMillis: Int? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "currency")
    val currency: String? = null,
    @Json(name = "primaryGenreName")
    val primaryGenreName: String? = null,
    @Json(name = "isStreamable")
    val isStreamable: Boolean? = null,
    @Json(name = "contentAdvisoryRating")
    val contentAdvisoryRating: String? = null,
    @Json(name = "collectionArtistName")
    val collectionArtistName: String? = null,
    @Json(name = "collectionArtistId")
    val collectionArtistId: Int? = null,
    @Json(name = "collectionArtistViewUrl")
    val collectionArtistViewUrl: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "shortDescription")
    val shortDescription: String? = null,
    @Json(name = "longDescription")
    val longDescription: String? = null,
): Parcelable {

    companion object {
        const val TABLE_NAME = "media_item_table"
    }

    val existingName: String? get() = collectionName ?: collectionCensoredName ?: trackName ?: trackCensoredName
    val existingPrice: Double? get() = collectionPrice ?: trackPrice ?: price
    val formattedDate: String? get() = releaseDate?.toDate(DATE_TIME_FORMAT)?.toString(DATE_DOT_TEXT_FORMAT)
    val existingLongDescription: String? get() = longDescription ?: description
}