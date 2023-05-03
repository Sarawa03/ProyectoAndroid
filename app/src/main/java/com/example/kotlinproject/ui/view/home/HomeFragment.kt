package com.example.kotlinproject.ui.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeAdapter
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
    }

    private fun initUI() {
        binding.loading.isVisible = true

        viewModel.pokemonHomeViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            binding.swipe.isRefreshing = false
            binding.loading.isVisible = false
        })

        adapter = PokemonHomeAdapter(onItemSelected = {navigateToDetail(it)}, addFavPokemon = {addFavPokemon(it)}, unfavPokemon = {unfavPokemon(it)})
        binding.rvPokedex.setHasFixedSize(true)
        binding.rvPokedex.layoutManager = GridLayoutManager(this.context, 2)
        binding.rvPokedex.adapter = adapter

        viewModel.randomPokemons()

        binding.swipe.setColorSchemeColors(resources.getColor(R.color.pokedexColor), resources.getColor(R.color.loading2))
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            viewModel.randomPokemons()
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
        val mainActivity = activity as MainActivity
        mainActivity.showDetails(id)
    }

    private fun addFavPokemon(pokemonItem: PokemonItem){
        viewModel.addFavPokemon(pokemonItem)
    }

    private fun unfavPokemon(pokemonItem: String){
        viewModel.unfavPokemon(pokemonItem)
    }
}