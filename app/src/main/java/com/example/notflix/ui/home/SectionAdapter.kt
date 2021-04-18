package com.example.notflix.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.notflix.entity.TvShowEntity
import com.example.notflix.ui.movies.MoviesFragment
import com.example.notflix.ui.tvshow.TvShowFragment

class SectionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
   FragmentStateAdapter(fragmentManager,lifecycle){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment =
        when(position){
            0 -> MoviesFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    }
