package com.ridhoafni.favorite.di

import android.content.Context
import com.ridhoafni.codeinmoviedb.di.FavoriteModuleDependencies
import com.ridhoafni.favorite.FavoriteMovieFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteMovieFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}
