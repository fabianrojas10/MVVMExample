package com.mvvm.model.api

import com.mvvm.model.entity.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface MoviesApiService {
    @GET
    suspend fun getPopularMovies(@Url url: String): Response<MoviesResponse>
}