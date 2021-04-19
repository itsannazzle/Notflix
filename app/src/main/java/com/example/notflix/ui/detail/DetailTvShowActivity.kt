package com.example.notflix.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailTvShowBinding
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.TvShowEntity

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel : DetailMoviesViewModel by viewModels()
    companion object{
        const val EXTRA_TVSHOW = "TV"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val episodesAdapter = EpisodesAdapter()
        val tvshowId = intent.getParcelableExtra<TvShowEntity>(EXTRA_TVSHOW)
        if (tvshowId != null){
                viewModel.getSelectedTvShow(tvshowId.id_tvshow)
                showDetailShow(viewModel.showDetailTvShow())
            episodesAdapter.setEpisodes(viewModel.showEpisodes())
            with(binding.rvEps){
                layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
                adapter = episodesAdapter
            }
        }

    }

    private fun showDetailShow(tvShowEntity: TvShowEntity){
        binding.moviesTitle.text = tvShowEntity.title
        binding.moviesCountry.text = tvShowEntity.country
        binding.moviesDesc.text = tvShowEntity.overview
        binding.moviesDuration.text = tvShowEntity.duration
        binding.moviesGenre.text = tvShowEntity.genre
        binding.moviesRating.text = tvShowEntity.rating
        Glide.with(this)
                .load(tvShowEntity.poster)
                .into(binding.moviesPoster)
    }


}