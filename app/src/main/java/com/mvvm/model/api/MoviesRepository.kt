package com.mvvm.model.api

class MoviesRepository {
    suspend fun getPopularMovies() = API.apiService.getPopularMovies("https://api.themoviedb.org/3/movie/popular?api_key=633aa09a618d8a32947d0cb4499631c3")
}