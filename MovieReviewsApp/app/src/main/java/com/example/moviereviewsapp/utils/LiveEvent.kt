package com.example.moviereviewsapp.utils

import androidx.lifecycle.LiveData

open class DataLiveEvent<T> : LiveData<Event<T?>> {
    constructor() : super()
    constructor(value: Event<T>) : super(value)
}

class MutableDataLiveEvent<T> : DataLiveEvent<T> {
    constructor() : super()
    constructor(value: Event<T>) : super(value)

    fun call(value: T) {
        this.value = Event(value)
    }
}

open class LiveEvent : LiveData<Event<Int>>() {
}

class MutableLiveEvent : LiveEvent() {
    fun call() {
        value = Event(0)
    }
}

