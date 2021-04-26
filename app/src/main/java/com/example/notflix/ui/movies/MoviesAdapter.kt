package com.example.notflix.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.data.remote.response.ResultsItem
import com.example.notflix.databinding.ItemPosterBinding
import com.example.notflix.entity.PrevMoviesEntity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var moviesEntity = ArrayList<PrevMoviesEntity>()

    private var onItemCallback : OnItemCallback? = null

    interface OnItemCallback {
        fun onItemClicked(movies: PrevMoviesEntity)
    }

    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    fun addMovies(movies: List<PrevMoviesEntity>){
        moviesEntity.clear()
        moviesEntity.addAll(movies)

    }

    inner class MoviesViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies : PrevMoviesEntity){
            Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL + movies.poster)
                    .into(binding.previewPoster)
            itemView.setOnClickListener {
                onItemCallback?.onItemClicked(movies)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(ItemPosterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesEntity[position])
    }

    override fun getItemCount(): Int {
        return moviesEntity.size
    }


}