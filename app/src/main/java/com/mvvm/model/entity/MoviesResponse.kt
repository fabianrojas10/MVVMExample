package com.mvvm.model.entity

import com.google.gson.annotations.SerializedName

data class MoviesResponse ( @SerializedName("results") var results: ArrayList<Movie> )