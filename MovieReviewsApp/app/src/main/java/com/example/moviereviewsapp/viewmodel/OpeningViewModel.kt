package com.example.moviereviewsapp.viewmodel

import com.example.moviereviewsapp.view.fragment.MainMoviesFragmentDirections
import com.example.moviereviewsapp.view.fragment.OpeningFragmentDirections
import com.example.moviereviewsapp.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OpeningViewModel @Inject constructor() : BaseViewModel() {
    fun internetReviewClicked() {
        //nneds to be changed after segev finishes the API part
        navigate(OpeningFragmentDirections.actionOpeningFragmentToMainMoviesFragment())
    }

    fun myReviewClicked() {
        navigate(OpeningFragmentDirections.actionOpeningFragmentToMainMoviesFragment())
    }
}
