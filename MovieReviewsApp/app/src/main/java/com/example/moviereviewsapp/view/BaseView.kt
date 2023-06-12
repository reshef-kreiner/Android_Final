package com.example.moviereviewsapp.view

import androidx.annotation.StringRes
import com.example.moviereviewsapp.viewmodel.BaseViewModel

interface BaseView<out VM : BaseViewModel> {
    val viewModel: VM?

    fun onSetup()
    fun showError(@StringRes stringResId: Int)
    fun showMessage(@StringRes stringResId: Int)
}