package com.example.notflix.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.databinding.ActivityDetailBinding
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.movies.MoviesAdapter
import com.example.notflix.ui.movies.MoviesViewModel

class DetailMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel : DetailMoviesViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    companion object{
        const val EXTRA_MOVIEID = "MOVIE_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



        val intent = intent.extras
        if (intent != null){
            val movieId = intent.getInt(EXTRA_MOVIEID)
            viewModel.getSelectedMovie(movieId)
        }
        viewModel.showDetailMovie().observe(this, {
            movies -> showDetail(movies)

        })
        //showRecomendation()
    }

    private fun showDetail(movieId : MoviesEntity){
        binding.moviesTitle.text = movieId.title
        binding.moviesCountry.text = movieId.country
        binding.moviesDesc.text = movieId.overview
        binding.moviesDuration.text = movieId.duration.toString()
        binding.moviesGenre.text = movieId.genre
        binding.moviesRating.text = movieId.rating.toString()
        Glide.with(this)
                .load(BuildConfig.POSTER_URL + movieId.backDrop)
                .into(binding.moviesPoster)
    }

//    private fun showRecomendation(){
//        moviesAdapter = MoviesAdapter()
//        moviesAdapter.addMovies(moviesViewModel.getMovies())
//        with(binding.movieRec){
//            adapter = moviesAdapter
//            layoutManager = LinearLayoutManager(this@DetailMoviesActivity,LinearLayoutManager.HORIZONTAL,false)
//        }
//    }
}