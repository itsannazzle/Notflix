package com.nextint.favoritefeature.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notflix.R
import com.google.android.material.tabs.TabLayoutMediator
import com.nextint.favoritefeature.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    companion object {
        private var TAB_TITLE = intArrayOf(
            R.string.movies,
            R.string.tv_shoe
        )
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val favSecAdapter = FavoriteSectionAdapter(childFragmentManager,lifecycle)
        binding.homepagerFavorite.adapter = favSecAdapter
        TabLayoutMediator(binding.tabFavorite,binding.homepagerFavorite){ tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

        return binding.root
    }




}