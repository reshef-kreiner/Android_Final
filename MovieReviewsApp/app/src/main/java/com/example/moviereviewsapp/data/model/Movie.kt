package com.example.moviereviewsapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    val year: Int,
    val title: String,
    val review: String,
    val image: String?,
    @PrimaryKey(autoGenerate = true) val id: Int
) {
    constructor(title: String, year: Int, review: String, image: String?) : this(
        year,
        title,
        review,
        image,
        0
    )
}