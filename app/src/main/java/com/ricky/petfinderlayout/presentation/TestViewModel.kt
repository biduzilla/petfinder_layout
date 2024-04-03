package com.ricky.petfinderlayout.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.petfinderlayout.domain.use_cases.GetToken
import com.ricky.petfinderlayout.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getToken: GetToken
) : ViewModel() {

    fun onEvent(event: TestEvent) {
        when (event) {
            TestEvent.OnClick -> {
                Log.i("infoteste", "onClick")
                getToken().onEach { result ->
                    Log.i("infoteste", "Result")
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
    }
}