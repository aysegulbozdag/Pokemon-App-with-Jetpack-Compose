package com.example.pokemonapp.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.pokemonapp.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.palette.graphics.Palette
import com.example.pokemonapp.data.models.PokedexListEntry


@HiltViewModel
class PokemonListviewModel @Inject constructor(private val repository: PokemonRepository) :
    ViewModel() {
    private var curPage = 0

    var pokemonList= mutableStateOf<List<PokedexListEntry>>(listOf())
    var loadError =  mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)




    fun calDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }


}