package com.ricky.petfinderlayout.presentation.home

import com.ricky.petfinderlayout.domain.model.Pet

data class HomeState(
    val isLoading: Boolean = false,
    val isDark: Boolean = false,
    val isLoadingMore: Boolean = false,
    val page: Int = 1,
    val pets: List<Pet> = emptyList(),
    val error: String = "",
    val loadMoreVisible: Boolean = true,
)
