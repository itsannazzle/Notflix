package com.example.notflix.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.data.local.entity.TvShowEntity
import com.example.notflix.databinding.FragmentTvShowBinding
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.example.notflix.values.Status

class TvShowFragment : Fragment() {
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tvShowAdapter: TvShowAdapter
    private val viewModel : TvShowViewModel by activityViewModels{
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            when(tvShow.status){
                Status.SUCCESS -> {
                    binding.progressCircular.visibility = View.GONE
                    tvShowAdapter.submitList(tvShow.data)
                    tvShowAdapter.notifyDataSetChanged()
                    binding.rvTvshow.adapter = tvShowAdapter
                    binding.rvTvshow.setHasFixedSize(true)
                }
                Status.LOADING -> binding.progressCircular.visibility = View.VISIBLE
                Status.ERROR -> Toast.makeText(activity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
            }

        })
        with(binding.rvTvshow){
            adapter = tvShowAdapter
            layoutManager = GridLayoutManager(requireContext(),2)
        }

        tvShowAdapter.setOnItemCallback(object : TvShowAdapter.OnItemCallback {
            override fun onItemClicked(tvshow: TvShowEntity) {
                val intent = Intent(activity,DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvshow.id_tvshow)
                startActivity(intent)
            }
        })
    }

}