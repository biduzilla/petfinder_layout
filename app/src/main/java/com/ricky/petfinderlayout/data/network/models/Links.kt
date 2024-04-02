package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class Links(
    @SerializedName("organization")
    val organization: Organization,
    @SerializedName("self")
    val self: Self,
    @SerializedName("type")
    val type: Type
)