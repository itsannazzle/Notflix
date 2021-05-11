package com.example.notflix.ui.tvshow

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.databinding.ItemPosterBinding
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.example.notflix.ui.movies.MoviesAdapter

class TvShowAdapter : PagedListAdapter<TvShowEntity,TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {
    private var tvEntity = ArrayList<TvShowEntity>()

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id_tvshow == newItem.id_tvshow
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }

        }
    }
    private var onItemCallback : OnItemCallback? = null

    interface OnItemCallback {
        fun onItemClicked(movies: TvShowEntity)
    }
    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    inner class TvShowViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow : TvShowEntity){
            Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL+tvshow.poster)
                    .into(binding.previewPoster)
            itemView.setOnClickListener{
                onItemCallback?.onItemClicked(tvshow)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowAdapter.TvShowViewHolder {
        return TvShowViewHolder(
            ItemPosterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

    }

//    override fun getItemCount(): Int {
//        return tvEntity.size
//    }


}