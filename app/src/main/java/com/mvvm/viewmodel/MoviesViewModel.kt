package com.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.R
import com.mvvm.model.api.MoviesRepository
import com.mvvm.model.entity.Movie
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {

    private val moviesRepository = MoviesRepository()
    private val picasso = Picasso.get()
    private val baseUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"

    private val _moviesState = MutableLiveData<MoviesState>()
    val moviesState : LiveData<MoviesState> = _moviesState

    fun getPopularMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val call = moviesRepository.getPopularMovies()
            if (call.isSuccessful) {
                val movies : MutableList<Movie> = ArrayList()
                movies.addAll(call.body()?.results ?: emptyList())
                movies.forEach {
                    it.imageRequest = picasso.load(baseUrl+it.poster_path)
                    it.iconFavorite = R.drawable.ic_no_favorite
                }
                _moviesState.postValue(MoviesState.SuccessGetMovies(movies))
            }else{
                _moviesState.postValue(MoviesState.ErrorGettingMovies)
            }
        }
    }

    fun onClickFavoriteIconMovie(movie: Movie) {
        with (movie) {
            isFavorite = !isFavorite
            iconFavorite =
                if (isFavorite) R.drawable.ic_favorite
                else R.drawable.ic_no_favorite
        }
    }
}

sealed class MoviesState {
    class SuccessGetMovies(val movies: MutableList<Movie>) : MoviesState()
    object ErrorGettingMovies : MoviesState()
}