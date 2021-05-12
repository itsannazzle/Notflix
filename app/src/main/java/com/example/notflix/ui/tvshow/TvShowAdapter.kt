package com.example.notflix.ui.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notflix.BuildConfig
import com.example.notflix.R
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.databinding.ItemPosterBinding

class TvShowAdapter : PagedListAdapter<TvShowEntity,TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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
        fun onItemClicked(tvShowEntity: TvShowEntity)
    }
    fun setOnItemCallback(onItemCallback: OnItemCallback){
        this.onItemCallback = onItemCallback
    }

    inner class TvShowViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow : TvShowEntity){
            Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL+tvshow.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.pic_nopic))
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