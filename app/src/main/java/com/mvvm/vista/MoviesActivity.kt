package com.mvvm.vista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.databinding.ActivityMoviesBinding
import com.mvvm.model.entity.Movie
import com.mvvm.viewmodel.MoviesState
import com.mvvm.viewmodel.MoviesViewModel
import com.mvvm.vista.recicler_view.MoviesAdapter

class MoviesActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMoviesBinding
    private val activityViewModel : MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initView()
    }

    private fun initView() {
        activityViewModel.getPopularMovies()
        activityViewModel.moviesState.observe(this) { state ->
            when (state) {
                is MoviesState.SuccessGetMovies -> {
                    initRecyclerView(state.movies)
                    viewBinding.progressCircular.visibility = GONE
                    viewBinding.moviesList.visibility = VISIBLE
                }
                is MoviesState.ErrorGettingMovies -> {
                    viewBinding.progressCircular.visibility = GONE
                    viewBinding.errorMessage.visibility = VISIBLE
                }
            }
        }
    }

    private fun initRecyclerView(movies: MutableList<Movie>) {
        viewBinding.moviesList.layoutManager = LinearLayoutManager(this)
        viewBinding.moviesList.adapter = MoviesAdapter(movies, activityViewModel)
    }
}