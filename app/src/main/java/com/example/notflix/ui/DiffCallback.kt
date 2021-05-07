package com.example.notflix.ui


import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import java.lang.IllegalArgumentException

class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is MoviesEntity -> oldItem.id_movies == (newItem as MoviesEntity).id_movies
            is TvShowEntity -> oldItem.id_tvshow == (newItem as TvShowEntity).id_tvshow
            else -> throw IllegalArgumentException("Unknown Entity")
        }
    }


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when(oldItem){
            is MoviesEntity -> oldItem == newItem as MoviesEntity
            is TvShowEntity -> oldItem == newItem as TvShowEntity
            else -> throw IllegalArgumentException("Unknown Entity")
        }
    }
}