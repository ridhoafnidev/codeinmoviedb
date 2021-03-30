package com.ridhoafni.core.data.remote.service

import com.ridhoafni.core.BuildConfig.API_KEY
import com.ridhoafni.core.data.local.entity.MovieEntity
import com.ridhoafni.core.data.remote.response.DataMovie
import com.ridhoafni.core.data.remote.response.GenreResponse
import com.ridhoafni.core.data.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    suspend fun getDiscoverMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
    ): GenreResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenres(
        @Query("with_genres") withGenres: String,
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("language") language: String? = "en-US",
        @Query("page") page: Int,
    ): MovieResponse

    @GET("movie/{id}?append_to_response=videos,reviews")
    suspend fun getMovie(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String? = API_KEY
    ): MovieEntity

}