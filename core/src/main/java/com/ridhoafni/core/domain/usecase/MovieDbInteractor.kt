package com.ridhoafni.core.domain.usecase

import com.ridhoafni.core.data.Resource
import com.ridhoafni.core.data.local.entity.MovieDetails
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.Genre
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.domain.repository.IMovieDbRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDbInteractor @Inject constructor(private val movieDbRepository: IMovieDbRepository) : MovieDbUseCase {
    override fun getDiscoverMovies(): Flow<ApiResponse<List<DataMovie>>> = movieDbRepository.getDiscoverMovies()
    override fun getGenres(): Flow<List<Genre>> = movieDbRepository.getGenres()
    override fun getMoviesByGenres(genreId: String): Flow<ApiResponse<List<DataMovie>>> = movieDbRepository.getMoviesByGenres(genreId)
    override fun getMovie(movieId: Long): Flow<Resource<MovieDetails>> = movieDbRepository.getMovie(movieId)
}