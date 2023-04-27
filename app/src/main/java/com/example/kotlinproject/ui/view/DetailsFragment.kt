package com.example.kotlinproject.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentDetailsBinding


private const val ID_POKEMON = "id"
//private const val IS_FAV = "isFav"//TODO

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!


    private var idPokemon: String? = null
    //private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idPokemon = it.getString(ID_POKEMON)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        @JvmStatic
        //fun newInstance(idPokemon: String, param2: String) =
        fun newInstance(idPokemon: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_POKEMON, idPokemon)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }
}