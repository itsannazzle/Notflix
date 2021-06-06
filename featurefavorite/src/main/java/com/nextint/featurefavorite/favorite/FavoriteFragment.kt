package com.nextint.featurefavorite.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notflix.R
import com.google.android.material.tabs.TabLayoutMediator
import com.nextint.featurefavorite.databinding.FragmentFavoriteBinding


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favSecAdapter = FavoriteSectionAdapter(childFragmentManager,lifecycle)
        binding.homepagerFavorite.adapter = favSecAdapter
        TabLayoutMediator(binding.tabFavorite,binding.homepagerFavorite){ tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private var TAB_TITLE = intArrayOf(
            R.string.movies,
            R.string.tv_shoe
        )
    }
}