package com.example.kotlinproject.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.domain.model.PokeSprites
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.domain.model.StatName
import com.example.kotlinproject.domain.model.StatsItem
import com.example.kotlinproject.domain.model.TypeItem
import com.example.kotlinproject.domain.model.TypeListItem
import com.example.kotlinproject.ui.view.DetailsFragment
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeAdapter
import com.example.kotlinproject.ui.view.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapter: PokemonHomeAdapter
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initTestValues()

    }

    private fun initTestValues() {
        val listPokemon = listOf(
            PokemonItem(
                "1",
                "bulbasaur",
                "7",
                "69",
                sprites = PokeSprites("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
                listOf(
                    TypeListItem(TypeItem("grass")),
                    TypeListItem(TypeItem("poison"))
                ),
                listOf(
                    StatsItem("45", StatName("hp")),
                    StatsItem("49", StatName("attack")),
                    StatsItem("49", StatName("defense")),
                    StatsItem("65", StatName("special-attack")),
                    StatsItem("65", StatName("special-defense")),
                    StatsItem("45", StatName("speed")),
                )

            )
        )

        adapter.updateList(listPokemon)
    }

    private fun initUI() {

        viewModel.pokemonHomeViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })

        adapter = PokemonHomeAdapter({navigateToDetail(it)})
        binding.rvPokedex.setHasFixedSize(true)
        binding.rvPokedex.layoutManager = LinearLayoutManager(this.context)
        binding.rvPokedex.adapter = adapter

        binding.btnClick.setOnClickListener {
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.nav_host_fragment, DetailsFragment())
            fragmentTransaction?.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun navigateToDetail(id: String){
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment, DetailsFragment())
        fragmentTransaction?.commit()
    }

}