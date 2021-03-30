package com.ridhoafni.core.di

import com.ridhoafni.core.data.MovieDbRepository
import com.ridhoafni.core.domain.repository.IMovieDbRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(movieDbRepository: MovieDbRepository): IMovieDbRepository
}