package com.example.notflix.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.databinding.FragmentMoviesFragmentBinding
import com.example.notflix.core.domain.model.MoviesModel
import com.example.notflix.ui.detail.DetailMoviesActivity
import com.example.notflix.ui.favorite.UseableAdapter
import com.example.notflix.values.ResourceData
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {
    private lateinit var binding: FragmentMoviesFragmentBinding
    private lateinit var adapter : UseableAdapter<MoviesModel>
    private val viewModel : MoviesViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentMoviesFragmentBinding.inflate(inflater, container, false)

        if (activity != null){
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.showTrendingMovies().observe(viewLifecycleOwner,{
                trending ->
                when(trending){
                    is ResourceData.success -> {
                        binding.progressCircular.visibility = View.GONE
                        adapter.submitList(trending.data)
                        adapter.notifyDataSetChanged()
                        binding.rvMovies.adapter = adapter
                        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(),2)
                        binding.rvMovies.setHasFixedSize(true)
                    }
                    is ResourceData.error -> Toast.makeText(activity, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    is ResourceData.loading -> binding.progressCircular.visibility = View.VISIBLE
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