package com.ricky.petfinderlayout.data.network.models


import com.google.gson.annotations.SerializedName
import com.ricky.petfinderlayout.domain.model.Pet
import com.ricky.petfinderlayout.utils.Utils.formatContacts
import com.ricky.petfinderlayout.utils.Utils.formatData
import com.ricky.petfinderlayout.utils.Utils.formatPhotos


data class Animal(
    @SerializedName("age")
    val age: String?,
    @SerializedName("attributes")
    val attributes: Attributes?,
    @SerializedName("breeds")
    val breeds: Breeds?,
    @SerializedName("coat")
    val coat: String?,
    @SerializedName("colors")
    val colors: Colors?,
    @SerializedName("contact")
    val contact: Contact?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("distance")
    val distance: Double?,
    @SerializedName("environment")
    val environment: Environment?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("_links")
    val links: Links?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("organization_id")
    val organizationId: String?,
    @SerializedName("photos")
    val photos: List<Photo>?,
    @SerializedName("published_at")
    val publishedAt: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("videos")
    val videos: List<Video>?
)

fun Animal.toPet(): Pet {
    return Pet(
        id = formatData(id.toString()),
        age = formatData(age),
        breeds = formatData(breeds?.primary),
        colors = formatData(colors?.primary),
        contact = formatContacts(contact),
        description = formatData(description),
        distance = formatData(distance.toString()),
        gender = formatData(gender),
        name = formatData(name),
        photos = formatPhotos(photos),
        size = formatData(size),
        species = formatData(species),
        status = formatData(status),
        tags = tags?: emptyList(),
        type = formatData(type),
        currentPage = 0
    )
}