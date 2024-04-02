package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class Video(
    @SerializedName("embed")
    val embed: String
)