package com.ricky.petfinderlayout.data.network.models


import com.google.gson.annotations.SerializedName

data class Environment(
    @SerializedName("cats")
    val cats: Boolean,
    @SerializedName("children")
    val children: Boolean,
    @SerializedName("dogs")
    val dogs: Boolean
)