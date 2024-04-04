package com.ricky.petfinderlayout.presentation.home

import com.ricky.petfinderlayout.domain.model.Pet

data class HomeState(
    val isLoading: Boolean = true,
    val isLoadingMore: Boolean = true,
    val page: Int = 1,
    val pets: List<Pet> = emptyList(),
    val error: String = "",
    val loadMoreVisible: Boolean = true,
)
