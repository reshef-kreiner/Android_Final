package com.example.moviereviewsapp.view.fragment


import androidx.fragment.app.viewModels
import com.example.moviereviewsapp.databinding.FragmentOpeningBinding
import com.example.moviereviewsapp.viewmodel.OpeningViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import de.hdodenhof.circleimageview.CircleImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.moviereviewsapp.R

@AndroidEntryPoint
class OpeningFragment @Inject constructor() : BaseFragment<OpeningViewModel>() {
    override val contentView by lazy { _binding.root }
    override val viewModel: OpeningViewModel by viewModels()

    private val _binding by lazy { FragmentOpeningBinding.inflate(layoutInflater) }

    override fun onSetup() {
        _binding.apply {
            btnInternetReviews.setOnClickListener {
                viewModel.internetReviewClicked()
            }

            btnMyReviews.setOnClickListener {
                viewModel.myReviewClicked()
            }
        }

    }
}
