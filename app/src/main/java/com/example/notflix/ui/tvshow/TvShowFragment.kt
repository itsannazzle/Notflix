package com.example.notflix.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.R
import com.example.notflix.databinding.FragmentTvShowBinding

class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel : TvShowViewModel by activityViewModels()
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
        tvShowAdapter.addTvShow(viewModel.getTvShow())
        with(binding.rvTvshow){
            adapter = tvShowAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }
    }


}