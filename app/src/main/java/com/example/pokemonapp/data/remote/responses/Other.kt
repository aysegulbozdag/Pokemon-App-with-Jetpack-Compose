package com.example.pokemonapp.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    @SerializedName("offical-artwork")
    val officialArtwork: OfficialArtwork
)