package com.example.notflix.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentMoviesFragmentBinding
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailMoviesActivity
import com.example.notflix.ui.favorite.UseableAdapter
import com.example.notflix.values.Status

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesFragmentBinding
    private lateinit var adapter : UseableAdapter<MoviesEntity>
    private val viewModel : MoviesViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMoviesFragmentBinding.inflate(inflater, container, false)

        if (activity != null){
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.showTrendingMovies().observe(viewLifecycleOwner,{
                trending ->
                when(trending.status){
                    Status.SUCCESS -> {
                        binding.progressCircular.visibility = View.GONE
                        adapter.submitList(trending.data)
                        adapter.notifyDataSetChanged()
                        binding.rvMovies.adapter = adapter
                        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(),2)
                        binding.rvMovies.setHasFixedSize(true)
                    }
                    Status.ERROR -> Toast.makeText(activity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    Status.LOADING -> binding.progressCircular.visibility = View.VISIBLE
                }
            })
        }

        showTrending()

        return binding.root
    }

    private fun showTrending(){
        adapter = UseableAdapter{
            val intent = Intent(activity, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,it.id_movies)
            startActivity(intent)
        }

    }
}