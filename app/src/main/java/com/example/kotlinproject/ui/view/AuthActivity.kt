package com.example.kotlinproject.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authNavHostFragment = supportFragmentManager.findFragmentById(R.id.auth_nav_host_fragment) as NavHostFragment
        navController = authNavHostFragment.navController

    }

    fun goHome(email: String, provider: MainActivity.ProviderType){
        Log.i("PATATA", email)
        Log.i("PATATA", provider.name)
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(intent)
//        val bundle = Bundle()
//        bundle.putString("email", email)
//        bundle.putString("provider", provider.toString())
//        navController.navigate(R.id.homeFragment)
    }
    fun goSignUp(){
        navController.navigate(R.id.signUpFragment)
    }

    fun goLogIn() {
        navController.navigate(R.id.loginFragment)
    }
}