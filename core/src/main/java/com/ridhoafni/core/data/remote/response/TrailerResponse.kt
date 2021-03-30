package com.ridhoafni.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ridhoafni.core.data.local.entity.TrailerEntity
import kotlinx.parcelize.Parcelize

data class TrailerResponse (
    @field:SerializedName("results")
    var trailers: List<TrailerEntity>? = null
)

@Parcelize
data class DataTrailers(

    @field:SerializedName("id")
    var id: String,

    @field:SerializedName( "movie_id")
    var movieId: Int,

    @field:SerializedName("key")
    val key: String,

    @field:SerializedName("site")
    val site: String,

    @field:SerializedName("name")
    val name: String
) : Parcelable