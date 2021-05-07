package com.example.notflix.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notflix.R
import com.example.notflix.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    companion object {
        private var TAB_TITLE = intArrayOf(
                R.string.movies,
                R.string.tv_shoe
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val sectionAdapter = SectionAdapter(childFragmentManager,lifecycle)
        binding.homepager.adapter = sectionAdapter
        TabLayoutMediator(binding.tab,binding.homepager){ tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        return binding.root
    }


}