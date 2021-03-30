package com.ridhoafni.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val originalLanguage: String? = null
): Parcelable
