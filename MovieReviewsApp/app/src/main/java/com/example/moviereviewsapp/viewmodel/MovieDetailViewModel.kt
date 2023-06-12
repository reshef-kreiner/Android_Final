package com.example.moviereviewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsapp.data.Movie
import com.example.moviereviewsapp.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val repository: MovieRepository) :
    BaseViewModel() {
    val movie = MutableLiveData<Movie?>()

    fun onMovieId(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieById(movieId).collect {
                movie.value = it.getOrNull(0)
            }
        }
    }

    fun onBackClick() {
        navigateBack()
    }
}