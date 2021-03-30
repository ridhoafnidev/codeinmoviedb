package com.ridhoafni.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GenreResponse(
    @field:SerializedName("genres")
    val results: List<Genre>,
)

@Parcelize
data class Genre(
    @field:SerializedName("id")
    var id: Int,

    @field:SerializedName("name")
    var name: String
) : Parcelable