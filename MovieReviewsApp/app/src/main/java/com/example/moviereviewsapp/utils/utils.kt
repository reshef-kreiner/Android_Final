package com.example.moviereviewsapp.utils

fun String.isRTL() = if (length < 1) {
    true
} else {
    val directionality = Character.getDirectionality(get(0))
    directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT ||
            directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
}