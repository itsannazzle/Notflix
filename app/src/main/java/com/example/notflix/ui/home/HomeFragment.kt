package com.example.notflix.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notflix.R
import com.example.notflix.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
    private var _binding : FragmentHomeBinding? = null
    private var tabMediator : TabLayoutMediator? = null
    private val binding get() = _binding
    private var root : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding?.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionAdapter = SectionAdapter(childFragmentManager,lifecycle)
        binding?.homepager?.adapter = sectionAdapter
        tabMediator = binding?.tab?.let {
            binding?.homepager?.let { it1 ->
                TabLayoutMediator(it, it1){ tab, position ->
                    tab.text = resources.getString(TAB_TITLE[position])
                }
            }
        }
            tabMediator!!.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        root = null
    }
    companion object {
        private var TAB_TITLE = intArrayOf(
            R.string.movies,
            R.string.tv_shoe
        )
    }
}