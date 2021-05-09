package com.example.notflix.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notflix.BuildConfig
import com.bumptech.glide.Glide
import com.example.notflix.databinding.ItemPosterBinding
import com.example.notflix.data.local.entity.MoviesEntity

class MoviesAdapter : PagedListAdapter<MoviesEntity, MoviesAdapter.MoviesViewHolder>(DIFF_CALLBACK) {
    private var moviesEntity = ArrayList<MoviesEntity>()

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MoviesEntity>() {
            override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem.id_movies == newItem.id_movies
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemCallback : OnItemCallback? = null

    interface OnItemCallback {
        fun onItemClicked(movies: MoviesEntity)
    }

    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    fun addMovies(movies: List<MoviesEntity>){
        moviesEntity.clear()
        moviesEntity.addAll(movies)

    }

    inner class MoviesViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movies : MoviesEntity){
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
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return moviesEntity.size
    }


}