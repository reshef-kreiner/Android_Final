package com.example.moviereviewsapp.di.module

import android.content.Context
import androidx.room.Room
import com.example.moviereviewsapp.data.Movie
import com.example.moviereviewsapp.data.MovieDao
import com.example.moviereviewsapp.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase): MovieDao = db.movieDao()

    @Provides
    @Singleton
    fun provideEntity(@ApplicationContext context: Context): Movie =
        Movie("", 0, "", null)
}