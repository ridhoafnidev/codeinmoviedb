package com.ridhoafni.codeinmoviedb.view.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ridhoafni.core.domain.model.Movie
import com.ridhoafni.core.domain.usecase.MovieDbUseCase

class DetailMovieViewModel @ViewModelInject constructor(private val movieDbRepository: MovieDbUseCase) : ViewModel() {
    fun getMovie(id: Long) = movieDbRepository.getMovie(id).asLiveData()
}