package com.example.moviereviewsapp.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.moviereviewsapp.utils.DataLiveEvent
import com.example.moviereviewsapp.utils.LiveEvent
import com.example.moviereviewsapp.utils.MutableDataLiveEvent
import com.example.moviereviewsapp.utils.MutableLiveEvent
import com.example.moviereviewsapp.utils.NavigationCommand


open class BaseViewModel : ViewModel() {
    private val _errorEvent = MutableDataLiveEvent<Int>()
    private val _messageEvent = MutableDataLiveEvent<Int>()
    private val _navigationEvent = MutableDataLiveEvent<NavigationCommand>()

    val errorEvent = _errorEvent as DataLiveEvent<Int>
    val messageEvent = _messageEvent as DataLiveEvent<Int>
    val navigationEvent = _navigationEvent as DataLiveEvent<NavigationCommand>


    open fun attach() {}
    open fun detach() {}

    fun onError(@StringRes errMessageRes: Int) {
        _errorEvent.call(errMessageRes)
    }

    fun onMessage(@StringRes messageRes: Int) {
        _messageEvent.call(messageRes)
    }

    fun navigate(navDirections: NavDirections) {
        _navigationEvent.call(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigationEvent.call(NavigationCommand.Back)
    }
}
