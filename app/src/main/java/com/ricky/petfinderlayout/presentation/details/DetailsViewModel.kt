package com.ricky.petfinderlayout.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.petfinderlayout.domain.use_cases.GetPet
import com.ricky.petfinderlayout.utils.Constants
import com.ricky.petfinderlayout.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPet: GetPet,
    private val saveStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            loadPet(petId.toInt())
        }
    }

    private fun loadPet(petId: Int) {
        getPet(petId).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = DetailState(
                        isLoading = false,
                        error = result.message ?: "Error Inesperado"
                    )
                }

                is Resource.Loading -> {
                    _state.value = DetailState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = DetailState(
                        pet = result.data,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}