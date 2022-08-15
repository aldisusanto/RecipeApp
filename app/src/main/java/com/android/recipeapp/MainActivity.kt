package com.android.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.recipeapp.databinding.ActivityMainBinding
import com.android.recipeapp.ui.Home.HomeRepository
import com.android.recipeapp.ui.Home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var homeRepository: HomeRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()


        val navController = findNavController(R.id.nav_host_home_fragment)
        binding.bottomNavigation.setupWithNavController(navController)

    }


}