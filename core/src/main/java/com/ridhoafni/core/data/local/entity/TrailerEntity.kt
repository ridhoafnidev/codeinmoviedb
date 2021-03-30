package com.ridhoafni.core.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "trailerentities",
            foreignKeys = [ForeignKey(entity = MovieEntity::class,
                parentColumns = ["id"],
                childColumns = ["movie_id"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
            )],
            indices = [Index(value = ["movie_id"])]
        )
data class TrailerEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "movie_id")
    var movieId: Long,

    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "site")
    val site: String,

    @ColumnInfo(name = "name")
    val name: String,
) : Parcelable