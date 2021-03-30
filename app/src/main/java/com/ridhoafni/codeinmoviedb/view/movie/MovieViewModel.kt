package com.ridhoafni.codeinmoviedb.view.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridhoafni.core.domain.usecase.MovieDbUseCase

class MovieViewModel @ViewModelInject constructor(private val movieDbRepository: MovieDbUseCase) : ViewModel()  {
    fun getDiscoverMovies() = movieDbRepository.getDiscoverMovies().asLiveData()
    fun getGenres() = movieDbRepository.getGenres().asLiveData()
    fun getMoviesByGenres(genreIds: String) = movieDbRepository.getMoviesByGenres(genreIds).asLiveData()
}