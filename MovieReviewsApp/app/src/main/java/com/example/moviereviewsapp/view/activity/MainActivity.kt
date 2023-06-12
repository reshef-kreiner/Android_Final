package com.example.moviereviewsapp.view.activity

import androidx.activity.viewModels
import com.example.moviereviewsapp.databinding.ActivityMainBinding
import com.example.moviereviewsapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {
    override val contentView by lazy { binding.root }
    override val viewModel: MainViewModel by viewModels()

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onSetup() {
    }
}

