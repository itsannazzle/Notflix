package com.example.notflix.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notflix.BuildConfig
import com.example.notflix.databinding.ItemPosterBinding
import com.example.notflix.entity.MoviesEntity
import com.example.notflix.entity.PrevTVEntity
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.example.notflix.ui.movies.MoviesAdapter

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var tvEntity = ArrayList<PrevTVEntity>()



    fun addTvShow(movies: List<PrevTVEntity>){
        tvEntity.clear()
        tvEntity.addAll(movies)

    }

    inner class TvShowViewHolder(private val binding: ItemPosterBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvshow : PrevTVEntity){
            Glide.with(itemView.context)
                    .load(BuildConfig.POSTER_URL + tvshow.poster)
                    .into(binding.previewPoster)
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
        holder.bind(tvEntity[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,DetailTvShowActivity::class.java)
            intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,tvEntity[position])
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return tvEntity.size
    }


}