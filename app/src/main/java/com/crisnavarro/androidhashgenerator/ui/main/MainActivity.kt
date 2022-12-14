package com.crisnavarro.androidhashgenerator.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.ui.setupActionBarWithNavController
import com.crisnavarro.androidhashgenerator.R
import com.crisnavarro.androidhashgenerator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AHGTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHost
        setupActionBarWithNavController(navHost.navController)
    }

}