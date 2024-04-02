package com.ricky.petfinderlayout.data.network.models

import com.google.gson.annotations.SerializedName


data class Attributes(
    @SerializedName("declawed")
    val declawed: Boolean,
    @SerializedName("house_trained")
    val houseTrained: Boolean,
    @SerializedName("shots_current")
    val shotsCurrent: Boolean,
    @SerializedName("spayed_neutered")
    val spayedNeutered: Boolean,
    @SerializedName("special_needs")
    val specialNeeds: Boolean
)