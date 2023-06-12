package com.example.moviereviewsapp.utils

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    val ifNew: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
}