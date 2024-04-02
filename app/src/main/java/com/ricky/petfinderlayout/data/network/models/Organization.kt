package com.ricky.petfinderlayout.data.network.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class Organization(
    @SerializedName("href")
    val href: String
)