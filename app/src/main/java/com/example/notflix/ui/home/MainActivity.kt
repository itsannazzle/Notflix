package com.example.notflix.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notflix.R
import com.example.notflix.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNav.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when(destination.id){
//                R.id.favoriteFragment ->{
//                   R.id.action_homeFragment_to_favoriteFragment
//                }
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


}