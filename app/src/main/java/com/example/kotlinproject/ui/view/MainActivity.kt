package com.example.kotlinproject.ui.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityMainBinding
import com.example.kotlinproject.data.model.FavPokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    enum class ProviderType{
        BASIC,
        GOOGLE
    }

    companion object{

        var email: String? = null
        var provider: String? = null
        var listFavorites: MutableList<FavPokemon> = mutableListOf()
    }

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra("email")
        provider = intent.getStringExtra("provider")
        prefs = getSharedPreferences(getString(R.string.prefs), Context.MODE_PRIVATE).edit()

        val bottomNavigationView = binding.bottomNavView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        saveEmailAndProvider()
        initListeners()
    }

    private fun saveEmailAndProvider() {
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

    }

    private fun initListeners() {

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

    fun logOut() {
        prefs.clear()
        prefs.apply()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}