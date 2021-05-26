package com.example.notflix.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailBinding
import com.example.notflix.ui.favorite.UseableAdapter
import com.nextint.core.BuildConfig
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var adapter: UseableAdapter<MoviesModel>
    private val viewModel : DetailMoviesViewModel by viewModel()
    private var state = false
    companion object{
        const val EXTRA_MOVIEID = "MOVIE_ID"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.progressCircular.visibility = View.VISIBLE
        val intent = intent.extras
        if (intent != null){
            val movieId = intent.getInt(EXTRA_MOVIEID)
            viewModel.getSelectedMovie(movieId)
        }
        viewModel.detailMovie.observe(this, {
            movies ->
                when (movies) {
                    is ResourceData.loading -> binding.progressCircular.visibility = View.VISIBLE
                    is ResourceData.success -> {
                        movies.data?.let { showDetail(it) }
                        state = movies.data!!.favorite
                        isFavorited(state)
                    }
                    is ResourceData.error -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
        })

        showTrending()
        viewModel.showTrendingMovies().observe(this,{
            trending ->
            when(trending){
                is ResourceData.success -> {
                    adapter.submitList(trending.data)
                    adapter.notifyDataSetChanged()
                    binding.movieRec.adapter = adapter
                    binding.progressCircular.visibility = View.GONE
                }
                is ResourceData.loading -> binding.progressCircular.visibility = View.VISIBLE
                is ResourceData.error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

        })

        binding.heart.setOnClickListener {
            viewModel.isFavoriteMovie()
        }

    }

    private fun showDetail(movieId : MoviesModel){
        binding.moviesTitle.text = movieId.title
        binding.moviesCountry.text = movieId.country
        binding.moviesDesc.text = movieId.overview
        binding.moviesDuration.text = StringBuilder("${movieId.duration}m")
        binding.moviesGenre.text = movieId.genre
        binding.moviesRating.text = movieId.rating.toString()
        Glide.with(this)
                .load(BuildConfig.POSTER_URL + movieId.backDrop)
                .apply(RequestOptions.placeholderOf(R.drawable.pic_nopic))
                .into(binding.moviesPoster)
    }

    private fun isFavorited(state : Boolean){
        if (state){
            binding.heart.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.heart.setImageResource(R.drawable.ic_heart)
        }
    }

    private fun showTrending(){
        adapter = UseableAdapter{
            val intent = Intent(this, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,it.id_movies)
            startActivity(intent)
        }
        with(binding.movieRec){
            layoutManager = LinearLayoutManager(this@DetailMoviesActivity,LinearLayoutManager.HORIZONTAL,false)
            setHasFixedSize(true)
        }
    }

}