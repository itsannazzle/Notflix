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
import com.nextint.core.BuildConfig
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.ui.UseableAdapter
import com.nextint.core.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {
    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private lateinit var adapter: UseableAdapter<MoviesModel>
    private val viewModel : DetailMoviesViewModel by viewModel()
    private var state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
        supportActionBar?.hide()

        binding?.progressCircular?.visibility = View.VISIBLE
        val intent = intent.extras
        if (intent != null) {
            viewModel.getDetailMovie(intent.getInt(EXTRA_MOVIEID)).observe(this,{
                    movies ->
                when (movies) {
                    is ResourceData.Loading -> binding?.progressCircular?.visibility = View.VISIBLE
                    is ResourceData.Success -> {
                        movies.data?.let {
                            showDetail(it)
                            binding?.progressCircular?.visibility = View.VISIBLE
                        }
                    }
                    is ResourceData.Error -> {
                        binding?.progressCircular?.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        showTrending()
        viewModel.showTrendingMovies().observe(this,{
                trending ->
            when(trending){
                is ResourceData.Success -> {
                    adapter.submitList(trending.data)
                    adapter.notifyDataSetChanged()
                    binding?.movieRec?.adapter = adapter
                    binding?.progressCircular?.visibility = View.GONE
                }
                is ResourceData.Loading -> binding?.progressCircular?.visibility = View.VISIBLE
                is ResourceData.Error -> {
                    binding?.progressCircular?.visibility = View.GONE
                    Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }


    private fun showDetail(movie : MoviesModel){
        binding?.moviesTitle?.text = movie.title
        binding?.moviesCountry?.text = movie.country
        binding?.moviesDesc?.text = movie.overview
        binding?.moviesDuration?.text = StringBuilder("${movie.duration}m")
        binding?.moviesGenre?.text = movie.genre
        binding?.moviesRating?.text = movie.rating.toString()
        binding?.moviesPoster?.let {
            Glide.with(this)
                .load(BuildConfig.POSTER_URL + movie.backDrop)
                .apply(RequestOptions.placeholderOf(R.drawable.pic_nopic))
                .into(it)
        }
        state = movie.favorite
        isFavorited(state)
        binding?.heart?.setOnClickListener {
            state = !state
            viewModel.isFavoriteMovie(movie, state)
            isFavorited(state)
        }
    }

    private fun isFavorited(state : Boolean){
        if (state){
            binding?.heart?.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding?.heart?.setImageResource(R.drawable.ic_heart)
        }
    }

    private fun showTrending(){
        adapter = UseableAdapter{
            val intent = Intent(this, DetailMoviesActivity::class.java)
            intent.putExtra(EXTRA_MOVIEID,it.id_movies)
            startActivity(intent)
        }
        with(binding?.movieRec){
            this?.layoutManager = LinearLayoutManager(this@DetailMoviesActivity,LinearLayoutManager.HORIZONTAL,false)
            this?.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    companion object{
        const val EXTRA_MOVIEID = "MOVIE_ID"
    }
}