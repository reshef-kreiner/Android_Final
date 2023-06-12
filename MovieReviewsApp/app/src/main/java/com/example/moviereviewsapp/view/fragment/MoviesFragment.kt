package com.example.moviereviewsapp.view.fragment

import androidx.fragment.app.viewModels
import com.example.moviereviewsapp.adapter.MovieAdapter
import com.example.moviereviewsapp.data.Movie
import com.example.moviereviewsapp.data.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MoviesFragment @Inject constructor() : BaseListFragment<Movie, MoviesViewModel>() {
    override val adapter by lazy { MovieAdapter() }
    override val viewModel: MoviesViewModel by viewModels()
}