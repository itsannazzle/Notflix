package com.example.notflix.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notflix.R
import com.example.notflix.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        private var TAB_TITLE = intArrayOf(
                R.string.movies,
                R.string.tv_shoe
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val sectionAdapter = SectionAdapter(supportFragmentManager,lifecycle)
        binding.homepager.adapter = sectionAdapter
        TabLayoutMediator(binding.tab,binding.homepager){ tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

    }
}