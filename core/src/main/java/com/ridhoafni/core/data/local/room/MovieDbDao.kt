package com.ridhoafni.core.data.local.room

import androidx.room.*
import com.ridhoafni.core.data.local.entity.*
import com.ridhoafni.core.data.remote.response.DataReviews
import com.ridhoafni.core.data.remote.response.DataTrailers
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDbDao {
    @Query("SELECT * FROM movieentities")
    fun getDiscoverMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: MovieEntity)

    @Query("DELETE FROM movieentities")
    suspend fun deleteMovies()

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieentities.id = :movieId")
    fun getMovie(movieId: Long): Flow<MovieDetails>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: MovieEntity)

    // Trailers
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTrailers(trailers: List<TrailerEntity>)

    // Reviews
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)
}