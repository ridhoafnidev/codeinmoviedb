package com.ridhoafni.core.di

import android.content.Context
import androidx.room.Room
import com.ridhoafni.core.data.local.room.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun providerDatabase(@ApplicationContext context: Context): MovieDbDatabase = Room.databaseBuilder(
        context,
        MovieDbDatabase::class.java, "MovieDb.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDbDao(database: MovieDbDatabase): MovieDbDao = database.movieDbMovieDao()
}