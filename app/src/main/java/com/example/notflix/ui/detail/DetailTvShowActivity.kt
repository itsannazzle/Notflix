package com.example.notflix.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.databinding.ActivityDetailTvShowBinding
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.ui.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel : DetailMoviesViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
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
        val tvshowId = intent.getParcelableExtra<TvShowEntity>(EXTRA_TVSHOW)
        if (tvshowId != null){
                viewModel.getSelectedTvShow(tvshowId.id_tvshow)
                viewModel.showDetailTvShow().observe(this,{
                    tvShow -> showDetailShow(tvShow)
                    binding.progressCircular.visibility = View.GONE
                })
            viewModel.showEpisodes().observe(this,{
                eps -> episodesAdapter.setEpisodes(eps)
                binding.rvEps.adapter = episodesAdapter
                binding.rvEps.layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
            })
        }

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
                .into(binding.moviesPoster)
    }


}