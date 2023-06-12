package com.chooloo.www.chooloolib.di.module

import com.example.moviereviewsapp.data.MovieDao
import com.example.moviereviewsapp.data.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [ApplicationModule.BindsModule::class])
class ApplicationModule {
    @Provides
    fun provideMovieRepository(movieDao: MovieDao): MovieRepository =
        MovieRepository(movieDao)

    @Module
    @InstallIn(SingletonComponent::class)
    interface BindsModule {
    }
}