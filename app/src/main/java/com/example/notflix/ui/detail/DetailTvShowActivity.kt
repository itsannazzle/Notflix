package com.example.notflix.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notflix.R
import com.example.notflix.databinding.ActivityDetailTvShowBinding
import com.nextint.core.BuildConfig
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.ui.EpisodesAdapter
import com.nextint.core.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvShowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel : DetailMoviesViewModel by viewModel()
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

        if (intent != null) {
            viewModel.getDetailTvShow(intent.getInt(EXTRA_TVSHOW)).observe(this,{
                    tvShow ->
                when(tvShow){
                    is ResourceData.success -> {
                        tvShow.data?.let {
                            showDetailShow(it)
                        }
                        binding.progressCircular.visibility = View.GONE
                    }
                    is ResourceData.error -> {
                        binding.progressCircular.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                    is ResourceData.loading -> binding.progressCircular.visibility = View.VISIBLE
                }
            })
        }
        viewModel.showEpisodes().observe(this,{
                eps -> episodesAdapter.setEpisodes(eps)
            binding.rvEps.adapter = episodesAdapter
            binding.rvEps.layoutManager = LinearLayoutManager(this@DetailTvShowActivity)
        })
    }

    private fun showDetailShow(tvShowEntity: TvShowModel){
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
        state = tvShowEntity.favorite
        isFavorited(state)
        binding.heart.setOnClickListener {
            state = !state
            viewModel.isFavoriteTv(tvShowEntity,state)
            isFavorited(state)
        }
    }

    private fun isFavorited(state : Boolean){
        if (state){
            binding.heart.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.heart.setImageResource(R.drawable.ic_heart)
        }
    }
}