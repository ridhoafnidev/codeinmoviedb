package com.ridhoafni.core.data.local

import com.ridhoafni.core.data.local.entity.MovieDetails
import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.local.entity.ReviewEntity
import com.ridhoafni.core.data.local.entity.TrailerEntity
import com.ridhoafni.core.data.local.room.MovieDbDao
import com.ridhoafni.core.data.remote.response.DataReviews
import com.ridhoafni.core.data.remote.response.DataTrailers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDbDao: MovieDbDao) {

    fun getDiscoverMovies(): Flow<List<MovieEntity>> = movieDbDao.getDiscoverMovies()

    fun getMovie(id: Long): Flow<MovieDetails> = movieDbDao.getMovie(id)

    suspend fun insertMovies(movie: MovieEntity) {
        movieDbDao.insertMovie(movie)
        insertTrailers(movie.trailersResponse?.trailers, movie.id)
        insertReviews(movie.reviewsResponse?.reviews, movie.id)
    }

    suspend fun insertTrailers(trailers: List<TrailerEntity>?, id: Long) {
        if (trailers != null) {
            for (trailer in trailers) {
                trailer.movieId = id
            }
            movieDbDao.insertTrailers(trailers)
        }
    }

    suspend fun insertReviews(reviews: List<ReviewEntity>?, id: Long) {
        if (reviews != null) {
            for (review in reviews) {
                review.movieId = id
            }
             movieDbDao.insertReviews(reviews)
        }
    }

}