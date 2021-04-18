package com.example.notflix.home

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.notflix.R
import com.example.notflix.movies.MoviesFragment

class SectionAdapter(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    companion object{
        private val TAB_TITLE = intArrayOf(
            R.string.movies,
            R.string.tv_shoe
        )
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLE[position])
    }
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> MoviesFragment()
            1 -> MoviesFragment()
        }
        return Fragment()
    }
}