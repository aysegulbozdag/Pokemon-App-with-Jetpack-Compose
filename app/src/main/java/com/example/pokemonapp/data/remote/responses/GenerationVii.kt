package com.example.pokemonapp.data.remote.responses

import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: İcons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon
)