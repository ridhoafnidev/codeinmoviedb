package com.ridhoafni.core.data

import com.ridhoafni.core.data.local.LocalDataSource
import com.ridhoafni.core.data.local.entity.MovieDetails
import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.remote.RemoteDataSource
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.Genre
import com.ridhoafni.core.domain.repository.IMovieDbRepository
import com.ridhoafni.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDbRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
    ) : IMovieDbRepository {

    override fun getDiscoverMovies(): Flow<ApiResponse<List<DataMovie>>>{
        return remoteDataSource.getDiscoverMovies()
    }

    override fun getMoviesByGenres(genreIds: String): Flow<ApiResponse<List<DataMovie>>> = remoteDataSource.getMoviesByGenres(genreIds)

    override fun getGenres(): Flow<List<Genre>> = remoteDataSource.getGenres()

    override fun getMovie(movieId: Long): Flow<Resource<MovieDetails>> =
        object : NetworkBoundResource<MovieDetails, MovieEntity>(appExecutors){
            override fun loadFromDB(): Flow<MovieDetails> = localDataSource.getMovie(movieId)

            override fun shouldFetch(data: MovieDetails?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieEntity>> = remoteDataSource.getMovie(movieId)

            override suspend fun saveCallResult(data: MovieEntity) {
                localDataSource.insertMovies(data)
            }
        }.asFlow()

}