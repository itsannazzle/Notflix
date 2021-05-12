package com.example.notflix.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.data.local.entity.MoviesEntity
import com.example.notflix.databinding.FragmentFavMovieBinding
import com.example.notflix.ui.UseableAdapter
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailMoviesActivity


class FavMovieFragment : Fragment() {
    private lateinit var binding: FragmentFavMovieBinding
    private lateinit var adapter: UseableAdapter<MoviesEntity>
    private val viewModel : FavoriteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }
    private val moviesEntity = MoviesEntity()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavMovieBinding.inflate (inflater, container, false)
        showFavMovie(moviesEntity)
        viewModel.showFavMovie().observe(viewLifecycleOwner,{
            favMovie ->
            adapter.submitList(favMovie)
            adapter.notifyDataSetChanged()
            binding.rvFavMovie.adapter = adapter

        })

        return binding.root
    }

    private fun showFavMovie(favMovie : MoviesEntity){
        adapter = UseableAdapter {
            val intent = Intent(activity,DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,favMovie.id_movies)
            startActivity(intent)
        }
        with(binding.rvFavMovie){
            adapter = adapter
            adapter?.notifyDataSetChanged()
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)

        }

    }

}