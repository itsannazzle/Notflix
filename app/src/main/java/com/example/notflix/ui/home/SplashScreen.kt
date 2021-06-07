package com.example.notflix.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.notflix.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private var _binding: ActivitySplashScreenBinding? = null
    private val binding get() = _binding
    private var root : View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        root = binding?.root
        setContentView(root)
        supportActionBar?.hide()
        binding?.splash?.animate()?.setDuration(1500)?.alpha(1f)?.withEndAction {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            this.finish()
        }
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}