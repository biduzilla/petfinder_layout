package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName
import com.ricky.petfinderlayout.data.network.models.Address


data class Contact(
    @SerializedName("address")
    val address: Address,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String
)