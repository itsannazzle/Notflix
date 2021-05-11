package com.example.notflix.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.notflix.R
import com.example.notflix.databinding.FragmentFavoriteBinding
import com.example.notflix.ui.ViewModelFactory
import com.example.notflix.ui.home.HomeFragment
import com.google.android.material.tabs.TabLayoutMediator


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel : FavoriteViewModel by activityViewModels{
        ViewModelFactory.getInstance(requireActivity())
    }
    companion object {
        private var TAB_TITLE = intArrayOf(
            R.string.movies,
            R.string.tv_shoe
        )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val favSecAdapter = FavoriteSectionAdapter(childFragmentManager,lifecycle)
        binding.homepagerFavorite.adapter = favSecAdapter
        TabLayoutMediator(binding.tabFavorite,binding.homepagerFavorite){ tab, position ->
            tab.text = resources.getString(FavoriteFragment.TAB_TITLE[position])
        }.attach()

        return binding.root
    }

    private fun checkFavorite(){
        viewModel.checkFavorite()
    }


}