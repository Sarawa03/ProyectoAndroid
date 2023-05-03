package com.example.kotlinproject.ui.view.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentFavoritesBinding
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import com.example.kotlinproject.ui.view.favorites.recyclerview.PokemonFavoritesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel by viewModels<FavoritesViewModel>()
    private var _binding: FragmentFavoritesBinding? = null
    private lateinit var adapter: PokemonFavoritesAdapter
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root

    }

    private fun initUI() {
        adapter = PokemonFavoritesAdapter (onItemSelected = {navigateToDetail(it)}, addFavPokemon = {addFavPokemon(it)}, unfavPokemon = {unfavPokemon(it)})
        binding.rvPokedex.adapter = adapter
        binding.rvPokedex.setHasFixedSize(true)
        binding.rvPokedex.layoutManager = GridLayoutManager(this.context, 2)

        binding.progressBar.isVisible=true
        viewModel.showFavs()
        binding.progressBar.isVisible=false
        viewModel.pokemonFavoritesViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)

        })
    }

    private fun navigateToDetail(id: String){
        val mainActivity = activity as MainActivity
        mainActivity.showDetails(id)
    }

    private fun addFavPokemon(pokemonItem: PokemonItem){
        val mainActivity = activity as MainActivity
        val email = mainActivity.getEmail()
        viewModel.addFavPokemon(pokemonItem, email)
    }

    private fun unfavPokemon(pokemonItem: String){
        viewModel.unfavPokemon(pokemonItem)
    }


}