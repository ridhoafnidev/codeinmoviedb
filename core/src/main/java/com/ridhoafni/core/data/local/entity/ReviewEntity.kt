package com.ridhoafni.core.data.local.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "reviewentities",
    foreignKeys = [ForeignKey(entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movie_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["movie_id"])])

data class ReviewEntity (
    @PrimaryKey
    @SerializedName("id")
    var id: String,

    @ColumnInfo(name = "movie_id")
    var movieId: Long = 0,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("content")
    var content: String? = null,

    @SerializedName("url")
    var url: String? = null
)
