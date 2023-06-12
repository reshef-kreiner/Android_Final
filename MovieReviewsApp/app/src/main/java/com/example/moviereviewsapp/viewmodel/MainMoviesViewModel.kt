package com.example.moviereviewsapp.viewmodel

import com.example.moviereviewsapp.data.MovieRepository
import com.example.moviereviewsapp.view.fragment.MainMoviesFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    BaseViewModel() {
    fun onAddClick() {
        navigate(MainMoviesFragmentDirections.actionMainMoviesFragmentToAddItemFragment())
    }

    fun onDeleteClick() {
        movieRepository.deleteMovies()
    }
}