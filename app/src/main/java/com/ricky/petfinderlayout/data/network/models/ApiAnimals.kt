package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class ApiAnimals(
    @SerializedName("animals")
    val animals: List<Animal>,
    @SerializedName("pagination")
    val pagination: Pagination
)
