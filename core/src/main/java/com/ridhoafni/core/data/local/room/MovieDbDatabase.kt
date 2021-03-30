package com.ridhoafni.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.local.entity.ReviewEntity
import com.ridhoafni.core.data.local.entity.TrailerEntity

@Database(
    entities = [MovieEntity::class, ReviewEntity::class, TrailerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MovieDbDatabase : RoomDatabase() {
    abstract fun movieDbMovieDao(): MovieDbDao
}