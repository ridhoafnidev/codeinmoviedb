package com.ridhoafni.core.data.local.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.ridhoafni.core.data.remote.response.Genre
import com.ridhoafni.core.data.remote.response.ReviewResponse
import com.ridhoafni.core.data.remote.response.TrailerResponse

@Entity(tableName = "movieentities")
data class MovieEntity @JvmOverloads constructor (

    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Long = 0,

    @SerializedName("title")
    var title: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdropPath: String?,

    @SerializedName("overview")
    var overview: String?,

    @SerializedName("popularity")
    var popularity: Double = 0.toDouble(),

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble(),

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    var voteCount: Int = 0,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String?,

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "genres")
    @SerializedName("genres")
    var genres: List<Genre>? = null,

    @Ignore
    @SerializedName("videos")
    var trailersResponse: TrailerResponse? = null,

    @Ignore
    @SerializedName("reviews")
    var reviewsResponse: ReviewResponse? = null
){

    @SerializedName("original_language")
    var originalLanguage: String? = null
        get() = field?.substring(0, 1)?.toUpperCase() + field?.substring(1)

    constructor() : this(0,null,null,null,null,0.0,0.0,0,null)
}