package com.example.notflix.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentFavTvShowBinding
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.nextint.core.domain.model.TvShowModel
import org.koin.android.viewmodel.ext.android.sharedViewModel


class FavTvShowFragment : Fragment() {
    private lateinit var binding: FragmentFavTvShowBinding
    private lateinit var adapter: UseableAdapter<TvShowModel>
    private val viewModel by sharedViewModel<FavoriteViewModel>()

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