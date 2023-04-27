package com.example.kotlinproject.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetailsBinding
import com.example.kotlinproject.databinding.FragmentHomeBinding


//private const val type = "id"
//private const val IS_FAV = "isFav"//TODO

class DetailsFragment() : Fragment() {

    private var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!


    //private var types: String? = null
    //private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            types = it.getString(ID_POKEMON)
//            //param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

//    companion object {
//        @JvmStatic
//        //fun newInstance(idPokemon: String, param2: String) =
//        fun newInstance(idPokemon: String) =
//            DetailsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ID_POKEMON, idPokemon)
//                    //putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}