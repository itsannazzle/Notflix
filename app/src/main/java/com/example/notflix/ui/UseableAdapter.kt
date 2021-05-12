package com.example.notflix.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notflix.BuildConfig
import com.example.notflix.R
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.databinding.ItemPosterBinding

class UseableAdapter<T>(private val onClickListener : ((T) -> Unit)) : PagedListAdapter<T,UseableAdapter<T>.UseableViewHolder>(DiffCallback<T>()) {

    inner class UseableViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(watch: T?, onClickListener: (T) -> Unit){
            var poster : String? = null
            when(watch){
                is MoviesEntity -> poster = watch.poster.toString()
                is TvShowEntity -> poster = watch.poster.toString()
            }
            with(binding){
                Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL + poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.pic_nopic))
                    .into(previewPoster)
            }
            itemView.setOnClickListener {
                if (watch != null) {
                    onClickListener(watch)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UseableViewHolder {
        return UseableViewHolder(ItemPosterBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: UseableViewHolder, position: Int) {
        val course = getItem(position)
        if (course != null){
            holder.bind(course,onClickListener)
        }
    }


}