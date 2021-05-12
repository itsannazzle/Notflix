package com.example.notflix.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.databinding.FragmentFavTvShowBinding
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailTvShowActivity


class FavTvShowFragment : Fragment() {
    private lateinit var binding: FragmentFavTvShowBinding
    private lateinit var adapter: UseableAdapter<TvShowEntity>
    private val viewModel : FavoriteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavTvShowBinding.inflate(inflater, container, false)

        viewModel.showFavTv().observe(viewLifecycleOwner,{
            favTvShow ->
            adapter.submitList(favTvShow)
            adapter.notifyDataSetChanged()
            binding.rvFavTv.adapter = adapter
        })

        showFavTv()

        return binding.root
    }

    private fun showFavTv(){
        adapter = UseableAdapter {
            val intent = Intent(activity,DetailTvShowActivity::class.java)
            intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,it.id_tvshow)
            startActivity(intent)
        }
        with(binding.rvFavTv){
            adapter?.notifyDataSetChanged()
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
        }
    }



}