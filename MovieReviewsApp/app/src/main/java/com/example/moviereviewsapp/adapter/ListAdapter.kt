package com.example.moviereviewsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewsapp.databinding.ListItemBinding
import com.example.moviereviewsapp.view.itemholder.ListItemHolder

abstract class ListAdapter<ItemType>() : RecyclerView.Adapter<ListItemHolder>() {
    private var _items: List<ItemType> = listOf()
    private var _onItemClickListener: (item: ItemType) -> Unit = {}

    var items: List<ItemType>
        get() = _items
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            _items = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListItemHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        val dataItem = getItem(position)
        holder.apply {
            setOnClickListener { _onItemClickListener.invoke(dataItem) }
            onBindListItem(this, dataItem, position)
        }
    }

    override fun getItemCount() = _items.size

    private fun getItem(position: Int) = _items[position]

    fun setOnItemClickListener(onItemClickListener: (item: ItemType) -> Unit) {
        _onItemClickListener = onItemClickListener
    }

    abstract fun onBindListItem(listItemHolder: ListItemHolder, item: ItemType, position: Int)
}