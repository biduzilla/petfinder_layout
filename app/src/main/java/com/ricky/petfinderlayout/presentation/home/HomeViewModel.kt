package com.ricky.petfinderlayout.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.domain.use_cases.GetPets
import com.ricky.petfinderlayout.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPets: GetPets,
    private val dataStore: DataStoreUtil
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadPets()
        viewModelScope.launch {
            dataStore.getTheme().collect { isDark ->
                _state.update {
                    it.copy(
                        isDark = isDark
                    )
                }
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnChangeTheme -> {
                Log.i("infoteste", "onEvent: onCHangeTHeme")
                viewModelScope.launch {
                    dataStore.saveTheme(isDark = !_state.value.isDark)
                }
            }

            HomeEvent.OnLoadMore -> {
                _state.update {
                    it.copy(
                        page = _state.value.page + 1
                    )
                }
                loadPets()
            }
        }

    }

    private fun loadPets() {
        getPets(_state.value.page).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message!!,
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoadingMore = true
                        )
                    }
                }

                is Resource.Success -> {
                    val pets = _state.value.pets.toMutableList()
                    pets.addAll(result.data ?: emptyList())
                    _state.update {
                        it.copy(
                            pets = pets,
                            isLoadingMore = false,
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}