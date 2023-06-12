package com.example.moviereviewsapp.view.fragment

import android.net.Uri
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.moviereviewsapp.databinding.MovieDetailBinding
import com.example.moviereviewsapp.utils.isRTL
import com.example.moviereviewsapp.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment @Inject constructor() : BaseFragment<MovieDetailViewModel>() {
    override val contentView by lazy { binding.root }
    override val viewModel: MovieDetailViewModel by viewModels()

    private val binding by lazy { MovieDetailBinding.inflate(layoutInflater) }
    private val navArgs: MovieDetailFragmentArgs by navArgs()

    override fun onSetup() {
        binding.movieDetailBackButton.setOnClickListener {
            viewModel.onBackClick()
        }

        viewModel.apply {
            onMovieId(navArgs.movieId)

            movie.observe(this@MovieDetailFragment) {
                binding.apply {
                    it?.title?.let { movieDetailTitle.text = it }
                    it?.review?.let { movieDetailReview.text = it }
                    it?.year?.let { movieDetailYear.text = it.toString() }
                    it?.image?.let { movieDetailImage.setImageURI(Uri.parse(it)) }
                }
            }
        }
    }
}