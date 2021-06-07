package com.nextint.featurefavorite.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notflix.ui.detail.DetailTvShowActivity
import com.nextint.core.domain.model.TvShowModel
import com.nextint.core.ui.UseableAdapter
import com.nextint.featurefavorite.databinding.FragmentFavTvShowBinding
import com.nextint.featurefavorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class FavTvShowFragment : Fragment() {
    private var _binding: FragmentFavTvShowBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    private lateinit var adapter: UseableAdapter<TvShowModel>
    private val viewModel by sharedViewModel<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavTvShowBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteModule)
        viewModel.showFavTv().observe(viewLifecycleOwner,{
                favTvShow ->
            adapter.submitList(favTvShow)
            adapter.notifyDataSetChanged()
            binding?.rvFavTv?.adapter = adapter
        })
        showFavTv()
    }

    private fun showFavTv(){
        adapter = UseableAdapter {
            val intent = Intent(activity,DetailTvShowActivity::class.java)
            intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW,it.id_tvshow)
            startActivity(intent)
        }
        with(binding?.rvFavTv){
            adapter.notifyDataSetChanged()
            this?.layoutManager = GridLayoutManager(requireContext(),2)
            this?.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        unloadKoinModules(favoriteModule)
    }
}