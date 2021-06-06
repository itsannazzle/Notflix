package com.nextint.core.ui


import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.domain.model.TvShowModel
import java.lang.IllegalArgumentException

class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return when (oldItem) {
            is MoviesModel -> oldItem.id_movies == (newItem as MoviesModel).id_movies
            is TvShowModel -> oldItem.id_tvshow == (newItem as TvShowModel).id_tvshow
            else -> throw IllegalArgumentException("Unknown Entity")
        }
    }


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return when(oldItem){
            is MoviesModel -> oldItem == newItem as MoviesModel
            is TvShowModel -> oldItem == newItem as TvShowModel
            else -> throw IllegalArgumentException("Unknown Entity")
        }
    }
}