package com.example.notflix.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notflix.R
import com.example.notflix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}