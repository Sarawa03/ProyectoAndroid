package com.example.kotlinproject.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.domain.model.FavPokemon
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    enum class ProviderType{
        BASIC
    }

    companion object{
        var email: String? = null
        var provider: String? = null
        var listFavorites: MutableList<FavPokemon> = mutableListOf()
    }

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("email")
        provider = intent.getStringExtra("provider")

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

    fun getEmail(): String{
        Log.i("DEBUGGING", email!!)
        return email!!
//        return ""
    }
    fun getProvider(): String{
        Log.i("DEBUGGING", provider!!)
        return provider!!
//        return ""
    }
    fun showDetails(id: String) {
        val bundle = Bundle()
        bundle.putString("id", id)
        navController.navigate(R.id.detailsFragment, bundle)
    }


}