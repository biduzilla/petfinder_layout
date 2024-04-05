package com.ricky.petfinderlayout.presentation.details

import com.ricky.petfinderlayout.domain.model.Pet

data class DetailState(
    val pet: Pet? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)