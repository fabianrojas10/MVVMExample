package com.mvvm.vista.recicler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.R
import com.mvvm.databinding.MovieBinding
import com.mvvm.model.entity.Movie
import com.mvvm.viewmodel.MoviesViewModel

class MoviesAdapter(
    private val moviesList: MutableList<Movie>,
    private val moviesViewModel: MoviesViewModel
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewHolderBinding = MovieBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie,
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = moviesList[position]

        with (holder.viewHolderBinding) {
            movie.imageRequest.into(movieImage)
            movieTitle.text = movie.title
            favoriteIcon.setImageResource(movie.iconFavorite)

            favoriteIcon.setOnClickListener {
                moviesViewModel.onClickFavoriteIconMovie(movie)
                favoriteIcon.setImageResource(movie.iconFavorite)
            }
        }
    }

    override fun getItemCount() = moviesList.size
}