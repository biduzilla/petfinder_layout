package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName
import com.ricky.petfinderlayout.data.network.models.Animal

data class ApiAnimal(
    @SerializedName("animal")
    val animal: Animal,
)
