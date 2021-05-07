package com.example.notflix.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentMoviesFragmentBinding
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailMoviesActivity

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesFragmentBinding
    private val viewModel : MoviesViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            binding.progressCircular.visibility = View.VISIBLE
            val moviesAdapter = MoviesAdapter()

            viewModel.showTrendingMovies().observe(viewLifecycleOwner,{
                trending ->
                binding.progressCircular.visibility = View.GONE
                moviesAdapter.addMovies(trending)
                moviesAdapter.notifyDataSetChanged()
                binding.rvMovies.setHasFixedSize(true)
                binding.rvMovies.adapter = moviesAdapter
            })

            //moviesAdapter.addMovies(viewModel.getMovies())
            with(binding.rvMovies){
                layoutManager = GridLayoutManager(requireContext(),2)
                adapter = moviesAdapter
                setHasFixedSize(true)
            }
            moviesAdapter.setOnItemCallback(object : MoviesAdapter.OnItemCallback {
                override fun onItemClicked(movies: MoviesEntity) {
                val intent = Intent(activity, DetailMoviesActivity::class.java)
                intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,movies.id_movies)
                startActivity(intent)
                }
            })
        }
    }
}