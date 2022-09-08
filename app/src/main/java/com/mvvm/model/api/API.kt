package com.mvvm.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object API {
    private const val baseUrl = "https://api.themoviedb.org/3/movie/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MoviesApiService = retrofit.create(MoviesApiService::class.java)
}