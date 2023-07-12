package com.example.pokemonapp.data.remote.responses

import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    val generationi: Generationİ,
    @SerializedName("generation-ii")
    val generationii: Generationİi,
    @SerializedName("generation-iii")
    val generationiii: Generationİii,
    @SerializedName("generation-iv")
    val generationiv: Generationİv,
    @SerializedName("generation-v")
    val generationv: GenerationV,
    @SerializedName("generation-vi")
    val generationvi: GenerationVi,
    @SerializedName("generation-vii")
    val generationvii: GenerationVii,
    @SerializedName("generation-i")
    val generation: GenerationViii
)