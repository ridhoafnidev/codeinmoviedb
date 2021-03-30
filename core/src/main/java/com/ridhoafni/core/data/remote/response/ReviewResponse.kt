package com.ridhoafni.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ridhoafni.core.data.local.entity.ReviewEntity
import kotlinx.parcelize.Parcelize

data class ReviewResponse (
    @field:SerializedName("results")
    var reviews: List<ReviewEntity>? = null
)

@Parcelize
data class DataReviews(

    @field:SerializedName("id")
    var id: String,

    @field:SerializedName( "movie_id")
    var movieId: Int,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("url")
    val url: String
) : Parcelable