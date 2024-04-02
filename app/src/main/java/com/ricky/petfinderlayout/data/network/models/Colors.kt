package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class Colors(
    @SerializedName("primary")
    val primary: String?,
    @SerializedName("secondary")
    val secondary: String?,
    @SerializedName("tertiary")
    val tertiary: String?
)