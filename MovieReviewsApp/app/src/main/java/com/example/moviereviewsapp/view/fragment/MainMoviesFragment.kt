package com.example.moviereviewsapp.view.fragment

import android.view.View.LAYOUT_DIRECTION_LTR
import android.view.View.LAYOUT_DIRECTION_RTL
import androidx.fragment.app.viewModels
import com.example.moviereviewsapp.databinding.MainMoviesBinding
import com.example.moviereviewsapp.utils.isRTL
import com.example.moviereviewsapp.viewmodel.MainMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainMoviesFragment @Inject constructor() : BaseFragment<MainMoviesViewModel>() {
    override val contentView by lazy { _binding.root }
    override val viewModel: MainMoviesViewModel by viewModels()

    private val _moviesFragment by lazy { MoviesFragment() }
    private val _binding by lazy { MainMoviesBinding.inflate(layoutInflater) }

    override fun onSetup() {
        _binding.apply {
            mainMoviesAddButton.setOnClickListener {
                viewModel.onAddClick()
            }

            mainMoviesDeleteButton.setOnClickListener {
                viewModel.onDeleteClick()
            }

            mainMovieBackButton.setOnClickListener {
                viewModel.onBackClick()
            }
        }

        childFragmentManager
            .beginTransaction()
            .add(_binding.mainMoviesListContainer.id, _moviesFragment)
            .commitNow()
    }
}