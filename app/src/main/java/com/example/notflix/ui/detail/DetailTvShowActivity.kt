package com.example.notflix.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailTvShowBinding
import com.example.notflix.entity.EpisodesEntity
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevTVEntity
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

        val episodesAdapter = EpisodesAdapter()
        val tvshowId = intent.getParcelableExtra<PrevTVEntity>(EXTRA_TVSHOW)
        if (tvshowId != null){
                viewModel.getSelectedTvShow(tvshowId.tv_id)
                viewModel.showDetailTvShow().observe(this,{
                    tvShow -> showDetailShow(tvShow)
                })
//            episodesAdapter.setEpisodes(viewModel.showEpisodes())
//            with(binding.rvEps){
//                layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
//                adapter = episodesAdapter
//            }
        }

    }

    private fun showDetailShow(tvShowEntity: TvShowEntity){
        binding.moviesTitle.text = tvShowEntity.title
        binding.moviesCountry.text = tvShowEntity.country
        binding.moviesDesc.text = tvShowEntity.overview
        binding.moviesDuration.text = tvShowEntity.duration.toString()
        binding.moviesGenre.text = tvShowEntity.genre
        binding.moviesRating.text = tvShowEntity.rating.toString()
        Glide.with(this)
                .load(BuildConfig.POSTER_URL + tvShowEntity.backDrop)
                .into(binding.moviesPoster)
    }


}