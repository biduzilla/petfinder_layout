package com.ricky.petfinderlayout.data.network.models


import com.google.gson.annotations.SerializedName


data class Organization(
    @SerializedName("href")
    val href: String
)