package com.example.notflix.ui.home

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notflix.R
import com.example.notflix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
//        val appBarConfiguration = AppBarConfiguration.Builder(R.id.homeFragment,
//            R.id.favoriteFragment,R.id.searchFragment,R.id.comingSoonFragment,R.id.moreFragment).build()
//        setupActionBarWithNavController(navController,appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)

    }
}