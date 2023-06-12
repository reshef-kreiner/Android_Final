package com.example.moviereviewsapp.adapter

import android.net.Uri
import com.example.moviereviewsapp.data.Movie
import com.example.moviereviewsapp.view.itemholder.ListItemHolder

class MovieAdapter : ListAdapter<Movie>() {
    override fun onBindListItem(listItemHolder: ListItemHolder, item: Movie, position: Int) {
        listItemHolder.apply {
            titleText = item.title
            captionText = item.review
            try {
                item.image?.let { setImageUri(Uri.parse(it)) }
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }
}