package com.example.moviereviewsapp.view.itemholder

import android.net.Uri
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereviewsapp.databinding.ListItemBinding

open class ListItemHolder(protected val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    protected val context get() = itemView.context

    var titleText: String?
        get() = binding.listItemTitle.text.toString()
        set(value) {
            binding.listItemTitle.text = value
        }

    var captionText: String?
        get() = binding.listItemCaption.text.toString()
        set(value) {
            binding.listItemCaption.text = value
            binding.listItemCaption.isVisible = value != null && value.isNotEmpty()
        }

    fun setOnClickListener(onClickListener: View.OnClickListener?) {
        binding.root.setOnClickListener(onClickListener)
    }

    fun setImageUri(imageUri: Uri?) {
        binding.listItemImage.setImageURI(imageUri)
    }
}
