package com.example.moviereviewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviereviewsapp.utils.MutableDataLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class ListViewModel<ItemType> : BaseViewModel() {
    private var _itemsCollectJob: Job? = null
    private var _itemsFlow: Flow<List<ItemType>>? = null

    val items = MutableLiveData<List<ItemType>>()
    val isEmpty = MutableLiveData(true)
    val isLoading = MutableLiveData(true)
    val itemClickedEvent = MutableDataLiveEvent<ItemType>()

    override fun attach() {
        updateItemsFlow()
    }

    protected fun updateItemsFlow() {
        _itemsCollectJob?.cancel()
        _itemsCollectJob = viewModelScope.launch {
            _itemsFlow = getItemsFlow()
            _itemsFlow?.collect(this@ListViewModel::onItemsChanged)
        }
    }

    open fun onItemsChanged(items: List<ItemType>) {
        isLoading.value = false
        isEmpty.value = items.isEmpty()
        this.items.value = items
    }

    open fun onItemClick(item: ItemType) {
        itemClickedEvent.call(item)
    }

    abstract fun getItemsFlow(): Flow<List<ItemType>>?
}