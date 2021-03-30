package com.ridhoafni.core.domain.repository

import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.data.local.entity.MovieDetails
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.Genre
import com.ridhoafni.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieDbRepository {
    fun getDiscoverMovies(): Flow<ApiResponse<List<DataMovie>>>
    fun getGenres(): Flow<List<Genre>>
    fun getMoviesByGenres(genreId: String): Flow<ApiResponse<List<DataMovie>>>
    fun getMovie(movieId: Long): Flow<Resource<MovieDetails>>
}