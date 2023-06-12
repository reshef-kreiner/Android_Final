package com.example.moviereviewsapp.viewmodel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsapp.R
import com.example.moviereviewsapp.data.Movie
import com.example.moviereviewsapp.data.MovieRepository
import com.example.moviereviewsapp.utils.MutableLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMovieViewModel @Inject constructor(private val repository: MovieRepository) :
    BaseViewModel() {
    private var _lastPickedImage: Uri? = null

    val pickImageEvent = MutableLiveEvent()
    val pickedImagePath = MutableLiveData<String>()

    fun onAddMovie(title: String, year: Int, review: String) {
        viewModelScope.launch {
            try {
                repository.addMovie(
                    Movie(
                        title = title,
                        year = year,
                        review = review,
                        image = _lastPickedImage?.toString()
                    )
                )
                onMessage(R.string.movie_save_success)
                navigateBack()
            } catch (e: Exception) {
                onError(R.string.movie_save_failure)
            }
        }
    }

    fun onPickImage() {
        pickImageEvent.call()
    }

    fun onImagePicked(imageUri: Uri?) {
        _lastPickedImage = imageUri
        imageUri?.path?.let { pickedImagePath.value = it }
    }

    fun onBackClick() {
        navigateBack()
    }
}