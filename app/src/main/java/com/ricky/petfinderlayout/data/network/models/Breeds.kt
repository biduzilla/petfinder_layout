package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class Breeds(
    @SerializedName("mixed")
    val mixed: Boolean,
    @SerializedName("primary")
    val primary: String?,
    @SerializedName("secondary")
    val secondary: String?,
    @SerializedName("unknown")
    val unknown: Boolean
)