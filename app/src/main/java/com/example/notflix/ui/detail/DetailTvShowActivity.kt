package com.example.notflix.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notflix.BuildConfig
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailTvShowBinding
import com.example.notflix.core.data.local.entity.TvShowEntity
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.values.Status

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel : DetailMoviesViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    private var state = false
    companion object{
        const val EXTRA_TVSHOW = "TV"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.progressCircular.visibility = View.VISIBLE
        val episodesAdapter = EpisodesAdapter()
        val intent = intent.extras
        if (intent != null){
            val tvshowId = intent.getInt(EXTRA_TVSHOW)
            viewModel.getSelectedTvShow(tvshowId)
        }
        viewModel.detailTvShow.observe(this,{
                    tvShow ->
                    when(tvShow.status){
                        Status.SUCCESS -> {
                            tvShow.data?.let { showDetailShow(it) }
                            binding.progressCircular.visibility = View.GONE
                            state = tvShow.data?.favorite ?: false
                            isFavorited(state)
                        }
                        Status.ERROR -> {
                            binding.progressCircular.visibility = View.GONE
                            Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                        Status.LOADING -> binding.progressCircular.visibility = View.VISIBLE
                    }
                })

        binding.heart.setOnClickListener {
            viewModel.isFavoriteTv()
        }
            viewModel.showEpisodes().observe(this,{
                eps -> episodesAdapter.setEpisodes(eps)
                binding.rvEps.adapter = episodesAdapter
                binding.rvEps.layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
            })

    }

    private fun showDetailShow(tvShowEntity: TvShowEntity){
        binding.moviesTitle.text = tvShowEntity.title
        binding.moviesCountry.text = tvShowEntity.country
        binding.moviesDesc.text = tvShowEntity.overview
        binding.moviesDuration.text = StringBuilder("${tvShowEntity.duration}m")
        binding.moviesGenre.text = tvShowEntity.genre
        binding.moviesRating.text = tvShowEntity.rating.toString()
        Glide.with(this)
                .load(BuildConfig.POSTER_URL + tvShowEntity.backDrop)
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
}