package com.example.notflix.ui.movies

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notflix.databinding.ItemPosterBinding
import com.example.notflix.entity.MoviesEntity

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private var moviesEntity = ArrayList<MoviesEntity>()

    private var onItemCallback : OnItemCallback? = null

    interface OnItemCallback {
        fun onItemClicked(movies: MoviesEntity)
    }

    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    fun addMovies(movies: List<MoviesEntity>){
        if (movies == null) return
        moviesEntity.clear()
        moviesEntity.addAll(movies)

    }

    inner class MoviesViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies : MoviesEntity){
            Glide.with(itemView.context)
                    .load(movies.poster)
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