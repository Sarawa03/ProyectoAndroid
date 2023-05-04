package com.example.kotlinproject.ui.view.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.databinding.FragmentLoginBinding
import com.example.kotlinproject.ui.view.AuthActivity
import com.example.kotlinproject.ui.view.MainActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        setup()
    }

    private fun initUI() {
        val analytics = FirebaseAnalytics.getInstance(this.requireContext())
        val bundle = Bundle()
        bundle.putString("message", "test")
        analytics.logEvent("InitUI", bundle)
    }


    private fun setup() {
        binding.btnLogIn.setOnClickListener {
            if (!binding.etEmail.text.isNullOrBlank() && !binding.etPassword.text.isNullOrBlank()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            showHome(it.result?.user?.email ?: "", MainActivity.ProviderType.BASIC)
                        }else showAlert()
                    }
            }
        }

        binding.btnSignUp.setOnClickListener {
            goToSignUp()
        }
    }

    private fun goToSignUp() {
        val authActivity = activity as AuthActivity
        authActivity.goSignUp()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: MainActivity.ProviderType){

        val authActivity = activity as AuthActivity
        authActivity.goHome(email, provider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


}