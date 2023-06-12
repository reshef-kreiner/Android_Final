package com.example.moviereviewsapp.data

import com.example.moviereviewsapp.MovieReviewsApp
import com.example.moviereviewsapp.view.fragment.MainMoviesFragmentDirections
import com.example.moviereviewsapp.viewmodel.ListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MovieRepository) :
    ListViewModel<Movie>() {
    override fun getItemsFlow(): Flow<List<Movie>> = repository.getMovies()

    override fun onItemClick(item: Movie) {
        super.onItemClick(item)
        navigate(MainMoviesFragmentDirections.actionMainMoviesFragmentToMovieDetailFragment(item.id))
    }
}