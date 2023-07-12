package com.example.pokemonapp.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Generationİv(
    @SerializedName("diamond-pearl")
    val diamondpearl: DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldsoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)