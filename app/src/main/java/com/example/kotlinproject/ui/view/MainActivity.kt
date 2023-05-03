package com.example.kotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    enum class ProviderType{
        BASIC
    }
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                binding.bottomNavView.menu.getItem(0).itemId -> navController.navigate(it.itemId)
                binding.bottomNavView.menu.getItem(1).itemId -> navController.navigate(it.itemId)
                binding.bottomNavView.menu.getItem(2).itemId -> navController.navigate(it.itemId)
            }
            true
        }
    }

    fun showDetails(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        navController.navigate(R.id.detailsFragment, bundle)
    }


}