package com.mvvm.model.entity

import com.squareup.picasso.RequestCreator

data class Movie (
    val id: Int,
    val title: String,
    val poster_path: String,
    var isFavorite: Boolean,
    var imageRequest: RequestCreator,
    var iconFavorite: Int
)