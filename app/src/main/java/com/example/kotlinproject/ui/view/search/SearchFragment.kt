package com.example.kotlinproject.ui.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinproject.R
import com.example.kotlinproject.databinding.FragmentHomeBinding
import com.example.kotlinproject.databinding.FragmentSearchBinding
import com.example.kotlinproject.domain.model.PokemonItem
import com.example.kotlinproject.ui.view.MainActivity
import com.example.kotlinproject.ui.view.home.recyclerview.PokemonHomeAdapter
import com.example.kotlinproject.ui.view.search.recyclerview.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel by viewModels<SearchViewModel>()
    private var _binding: FragmentSearchBinding? = null
    private lateinit var adapter: SearchAdapter
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.loading.isVisible=true
                viewModel.searchPokemonByName(query.orEmpty().lowercase())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //binding.loading.isVisible=true
                //viewModel.searchPokemonByName(query.orEmpty().lowercase())
                return false
            }
        })

        viewModel.searchViewModel.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
            binding.loading.isVisible = false
        })

        adapter = SearchAdapter(onItemSelected = {navigateToDetail(it)}, addFavPokemon = {addFavPokemon(it)}, unfavPokemon = {unfavPokemon(it)})
        binding.rvPokeSearch.setHasFixedSize(true)
        binding.rvPokeSearch.layoutManager = LinearLayoutManager(this.context)
        binding.rvPokeSearch.adapter = adapter
    }

    private fun navigateToDetail(id: String) {
        val mainActivity = activity as MainActivity
        mainActivity.showDetails(id)
    }

    private fun addFavPokemon(pokemonItem: PokemonItem){
        val mainActivity = activity as MainActivity
        val email = mainActivity.getEmail()
        viewModel.addFavPokemon(pokemonItem,email)
    }

    private fun unfavPokemon(pokemonItem: String){
        viewModel.unfavPokemon(pokemonItem)
    }


}