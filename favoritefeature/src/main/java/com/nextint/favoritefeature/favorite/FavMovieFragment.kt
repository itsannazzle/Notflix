package com.nextint.favoritefeature.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.ui.detail.DetailMoviesActivity
import com.nextint.core.domain.model.MoviesModel
import com.nextint.core.ui.UseableAdapter
import com.nextint.favoritefeature.databinding.FragmentFavMovieBinding
import com.nextint.favoritefeature.di.favoriteModule
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class FavMovieFragment : Fragment() {
    private var _binding: FragmentFavMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UseableAdapter<MoviesModel>
    private val viewModel by sharedViewModel<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavMovieBinding.inflate (inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        viewModel.showFavMovie().observe(viewLifecycleOwner,{
                favMovie ->
            adapter.submitList(favMovie)
            binding.rvFavMovie.adapter = adapter
        })

        showFavMovie()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        unloadKoinModules(favoriteModule)
    }

}