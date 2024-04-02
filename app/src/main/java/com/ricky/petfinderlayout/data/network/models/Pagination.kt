package com.ricky.petfinderlayout.data.network.models


import com.google.gson.annotations.SerializedName
import com.ricky.petfinderlayout.data.network.models.Links


data class Pagination(
    @SerializedName("count_per_page")
    val countPerPage: Int,
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)