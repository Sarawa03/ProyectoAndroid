package com.example.kotlinproject.ui.view

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinproject.databinding.FragmentDetailsBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.StatsItem
import com.example.kotlinproject.domain.model.TypeListItem
import com.example.kotlinproject.ui.viewmodel.DetailsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import kotlin.math.roundToInt


private const val ID_POKEMON = "id"
//private const val IS_FAV = "isFav"//TODO

@AndroidEntryPoint
class DetailsFragment() : Fragment() {

    private var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!
    private val pokemonDetailsViewModel by viewModels<DetailsViewModel>()


    private var idPokemon: String? = null
    //private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("PATATA", idPokemon.toString())
        pokemonDetailsViewModel.postPokemonDetails(idPokemon!!)

        pokemonDetailsViewModel.pokemonDetailsModel.observe(viewLifecycleOwner, Observer {
            loadUI(it)
        })

    }

    private fun loadUI(pokemon: PokemonItem) {
        Picasso.get().load(pokemon.sprites.imgFrontM).into(binding.ivPokemon)
        binding.tvPokemonName.text = pokemon.name
        binding.tvHeight.text = buildString {
        append("Height: ")
        append(pokemon.height)
        append("'")
    }
        binding.tvWeight.text = buildString {
            append("Weight: ")
            append(pokemon.height)
            append(" lbs")
        }
        binding.tvPokemonId.text = buildString {
            append("#")
            append(pokemon.id)
        }
        drawStats(pokemon.statsList)
        loadTypes(pokemon.typeListItem)
    }

    private fun loadTypes(typeListItem: List<TypeListItem>) {
        var sb = StringBuilder("Types: ")
        typeListItem.forEach {
            sb.append(it.type.name)
            sb.append(" ")
        }
        binding.tvTypes.text = sb.toString()
    }

    private fun drawStats(statsList: List<StatsItem>) {
        val views = listOf(binding.viewHp, binding.viewAttack, binding.viewDefense, binding.viewSpecialAttack, binding.viewSpecialDefense, binding.viewSpeed)

        for (i in views.indices){
            paintStats(views[i], statsList[i].baseStat)
        }
    }

    private fun paintStats(view: View, baseStat: String) {
        val params = view.layoutParams
        params.width = pxToDp(baseStat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

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

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
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