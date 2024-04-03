package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName

data class ApiAnimal(
    @SerializedName("animal")
    val animal: Animal,
)
