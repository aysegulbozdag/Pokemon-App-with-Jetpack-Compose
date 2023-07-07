package com.example.pokemonapp.pokemonlist

import androidx.lifecycle.ViewModel
import com.example.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListviewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {

}