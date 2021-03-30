package com.ridhoafni.core.data.local.entity

import java.util.ArrayList

import androidx.room.Embedded
import androidx.room.Relation

data class MovieDetails (

    @Embedded
    var movie: MovieEntity? = null,

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    var trailers: List<TrailerEntity> = ArrayList(),

    @Relation(parentColumn = "id", entityColumn = "movie_id")
    var reviews: List<ReviewEntity> = ArrayList()
)
