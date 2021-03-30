package com.ridhoafni.codeinmoviedb.di

import com.ridhoafni.core.domain.usecase.MovieDbInteractor
import com.ridhoafni.core.domain.usecase.MovieDbUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule{
    @Binds
    abstract fun provideMovieDbUseCase(movieDbInteractor: MovieDbInteractor): MovieDbUseCase
}