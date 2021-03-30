package com.ridhoafni.core.data.remote

import android.util.Log
import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.remote.response.ApiResponse
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.Genre
import com.ridhoafni.core.data.remote.response.GenreResponse
import com.ridhoafni.core.data.remote.service.ApiService
import com.ridhoafni.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getDiscoverMovies(): Flow<ApiResponse<List<DataMovie>>> {
        //EspressoIdlingResource.increment()
        return flow {
            try {
                //EspressoIdlingResource.decrement()
                val response = apiService.getDiscoverMovies(page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }


    fun getMoviesByGenres(genreIds: String): Flow<ApiResponse<List<DataMovie>>> {
        // EspressoIdlingResource.increment()
        return flow {
            try {
                // EspressoIdlingResource.decrement()
                val response = apiService.getMoviesByGenres(genreIds, page = 1)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                }else if(dataArray.isEmpty()){
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



    fun getGenres(): Flow<List<Genre>> {
        //EspressoIdlingResource.increment()
        return flow {
            try {
                //EspressoIdlingResource.decrement()
                val response = apiService.getGenres()
                emit(response.results)
            }catch (e: Exception){
                //emit(ApiResponse.Error(e.toString()))
                Timber.e("RemoteDataSource ${e.toString()}")
            }
        }
    }

    suspend fun getMovie(id: Long): Flow<ApiResponse<MovieEntity>> {
        //EspressoIdlingResource.increment()
        return flow {
            try {
                //EspressoIdlingResource.decrement()
                val response = apiService.getMovie(id)
                emit(ApiResponse.Success(response))
                Timber.d("masuk getMovie()")
                Log.e("masuk getMovie() 89", "test")
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource 91", e.toString())

            }
        }.flowOn(Dispatchers.IO)
    }

}