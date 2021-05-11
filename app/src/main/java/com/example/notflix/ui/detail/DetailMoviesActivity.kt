package com.example.notflix.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailBinding
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.movies.MoviesAdapter
import com.example.notflix.values.Status

class DetailMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private val viewModel : DetailMoviesViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    private val state = false
    companion object{
        const val EXTRA_MOVIEID = "MOVIE_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        moviesAdapter = MoviesAdapter()

        binding.progressCircular.visibility = View.VISIBLE
        val intent = intent.extras
        if (intent != null){
            val movieId = intent.getInt(EXTRA_MOVIEID)
            viewModel.getSelectedMovie(movieId)
        }
        viewModel.showDetailMovie().observe(this, {
            movies ->
            when(movies.status){
                Status.SUCCESS -> {
                    movies.data?.let { showDetail(it) }
                }
                Status.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> binding.progressCircular.visibility = View.VISIBLE
            }
        })

        viewModel.showTrendingMovies().observe(this,{
            trending ->
            when(trending.status){
                Status.SUCCESS -> {
                    moviesAdapter.submitList(trending.data)
                    binding.movieRec.adapter = moviesAdapter
                    binding.progressCircular.visibility = View.GONE
                    binding.movieRec.layoutManager = LinearLayoutManager(this@DetailMoviesActivity,LinearLayoutManager.HORIZONTAL,false)
                }
                Status.LOADING -> binding.progressCircular.visibility = View.VISIBLE
                Status.ERROR -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

        })

        isFavorited(state)
        binding.heart.setOnClickListener {
            viewModel.isFavorite(state)
        }

    }

    private fun showDetail(movieId : MoviesEntity){
        binding.moviesTitle.text = movieId.title
        binding.moviesCountry.text = movieId.country
        binding.moviesDesc.text = movieId.overview
        binding.moviesDuration.text = StringBuilder("${movieId.duration}m")
        binding.moviesGenre.text = movieId.genre
        binding.moviesRating.text = movieId.rating.toString()
        Glide.with(this)
                .load(BuildConfig.POSTER_URL + movieId.backDrop)
                .into(binding.moviesPoster)
    }

    private fun isFavorited(state : Boolean){
        if (state){
            binding.heart.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.heart.setImageResource(R.drawable.ic_heart)
        }


    }



//    private fun showRecomendation(){
//        moviesAdapter = MoviesAdapter()
//        binding.progressCircular.visibility = View.VISIBLE
//       viewModel.showTrendingMovies().observe(this,{
//           rec -> moviesAdapter.addMovies(rec)
//           binding.progressCircular.visibility = View.GONE
//       })
//        with(binding.movieRec){
//            adapter = moviesAdapter
//            layoutManager = LinearLayoutManager(this@DetailMoviesActivity,LinearLayoutManager.HORIZONTAL,false)
//        }
//    }
}