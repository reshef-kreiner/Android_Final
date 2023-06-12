package com.example.moviereviewsapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieDao: MovieDao) {
    fun getMovies(): Flow<List<Movie>> =
        movieDao.getMovies()

    fun getMovieById(movieId: Int): Flow<List<Movie>> =
        movieDao.getMovieById(movieId)

    fun addMovie(movie: Movie) {
        movieDao.addMovie(movie)
    }

    fun deleteMovies() {
        movieDao.deleteMovies()
    }
}