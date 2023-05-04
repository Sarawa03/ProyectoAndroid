package com.example.kotlinproject.ui.view.login

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentLoginBinding
import com.example.kotlinproject.ui.view.AuthActivity
import com.example.kotlinproject.ui.view.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val GOOGLE_SIGN_IN = 100

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
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.etEmail.text.toString(), binding.etPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "", MainActivity.ProviderType.BASIC)
                    } else showAlert()
                }
            }
        }

        binding.btnSignUp.setOnClickListener {
            goToSignUp()
        }

        binding.btnGoogle.setOnClickListener {
            val authActivity = activity as AuthActivity
            authActivity.googleSignUp()
//            googleSignUp()

        }
    }

    private fun goToSignUp() {
        val authActivity = activity as AuthActivity
        authActivity.goSignUp()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Error")
        builder.setMessage("Eror autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: MainActivity.ProviderType) {

        val authActivity = activity as AuthActivity
        authActivity.goHome(email, provider)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

//    fun googleSignUp() {
//        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        //TODO
//        val googleClient = GoogleSignIn.getClient(requireActivity(),googleConf)
//        googleClient.signOut()
//
//        startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(requestCode==GOOGLE_SIGN_IN){
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//
//            try {
//                val account = task.getResult(ApiException::class.java)
//                if(account!=null) {
//                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
//                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            val authActivity = activity as AuthActivity
//                            authActivity.goHome(account.email?:"", MainActivity.ProviderType.GOOGLE)
//                        } else showAlert()
//                    }
//                }
//            }catch (e: ApiException){
//                Log.i("PATATA", "EXCEPCION")
//                showAlert()
//            }
//
//        }
//    }



}