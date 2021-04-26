package com.example.notflix.ui.tvshow

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.R
import com.example.notflix.databinding.FragmentTvShowBinding
import com.example.notflix.ui.ViewModelFactory

class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel : TvShowViewModel by activityViewModels{
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        showTvShow()

        return binding.root
    }

    private fun showTvShow(){
        tvShowAdapter = TvShowAdapter()
        binding.progressCircular.visibility = View.VISIBLE
        viewModel.showTvShow().observe(viewLifecycleOwner,{
            tvShow ->
            binding.progressCircular.visibility = View.GONE
            tvShowAdapter.addTvShow(tvShow)
            tvShowAdapter.notifyDataSetChanged()
            binding.rvTvshow.adapter = tvShowAdapter
            binding.rvTvshow.setHasFixedSize(true)
        })
        with(binding.rvTvshow){
            adapter = tvShowAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }


}