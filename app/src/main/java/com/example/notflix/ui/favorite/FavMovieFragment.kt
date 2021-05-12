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
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.detail.DetailMoviesActivity


class FavMovieFragment : Fragment() {
    private lateinit var binding: FragmentFavMovieBinding
    private lateinit var adapter: UseableAdapter<MoviesEntity>
    private val viewModel : FavoriteViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireActivity())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavMovieBinding.inflate (inflater, container, false)

        viewModel.showFavMovie().observe(viewLifecycleOwner,{
            favMovie ->
            adapter.submitList(favMovie)
            binding.rvFavMovie.adapter = adapter
        })

        showFavMovie()
        return binding.root
    }

    private fun showFavMovie(){
        adapter = UseableAdapter {
            val intent = Intent(activity,DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_MOVIEID,it.id_movies)
            startActivity(intent)
        }
        with(binding.rvFavMovie){
            adapter?.notifyDataSetChanged()
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)

        }

    }

}