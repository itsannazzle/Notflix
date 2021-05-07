package com.example.notflix.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notflix.databinding.ItemEpisodesBinding
import com.example.notflix.data.local.entity.EpisodesEntity

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpsViewHolder>() {
    private val listEpisodes = ArrayList<EpisodesEntity>()

    fun setEpisodes(eps : List<EpisodesEntity>){
        listEpisodes.clear()
        listEpisodes.addAll(eps)
    }
    inner class EpsViewHolder(private val binding: ItemEpisodesBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(episodesEntity: EpisodesEntity){
            with(binding){
                epsTitle.text = episodesEntity.eps_title
                epsDesc.text = episodesEntity.eps_desc
                Glide.with(itemView.context)
                    .load(episodesEntity.thumnail)
                    .into(posterEps)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpsViewHolder {
        return EpsViewHolder(ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: EpsViewHolder, position: Int) {
        holder.bind(listEpisodes[position])
    }

    override fun getItemCount(): Int {
        return listEpisodes.size
    }
}