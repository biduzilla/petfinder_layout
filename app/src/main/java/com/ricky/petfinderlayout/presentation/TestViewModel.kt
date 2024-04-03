package com.ricky.petfinderlayout.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.domain.use_cases.GetPets
import com.ricky.petfinderlayout.domain.use_cases.GetToken
import com.ricky.petfinderlayout.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getToken: GetToken,
    private val getPets: GetPets,
    private val dataStore: DataStoreUtil
) : ViewModel() {

    private var token: String = ""

    fun onEvent(event: TestEvent) {
        when (event) {
            TestEvent.OnToken -> loadToken()
            TestEvent.OnPets -> loadPets()
        }
    }

    init {
        viewModelScope.launch {
            dataStore.getToken().collect {
                token = it
            }
        }
    }

    private fun loadToken() {
        viewModelScope.launch {
            getToken()?.let {
                val token = "${it.tokenType} ${it.accessToken}"
                Log.i("infoteste", "token: $token")
            }
        }
    }

    private fun loadPets() {
        Log.i("infoteste", "loadPets")
        getPets(page = 1, token = token).onEach { result ->
            Log.i("infoteste", "result")
            when (result) {
                is Resource.Error -> {
                    Log.i("infoteste", "Error: ${result.message}")
                }

                is Resource.Loading -> {
                    Log.i("infoteste", "Loading")
                }

                is Resource.Success -> {
                    Log.i("infoteste", "Sucess: ${result.data}")
                }
            }
        }.launchIn(viewModelScope)
    }
}