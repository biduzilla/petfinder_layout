package com.ricky.petfinderlayout.domain.use_cases

import android.util.Log
import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.domain.repository.TokenRepository
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repository: TokenRepository,
) {
    suspend operator fun invoke(): AccessToken? {
        try {
            val result = repository.fetchAccessToken()

            if (result.isSuccessful) {
                result.body()?.let { token ->
                    return token
                }
            }
        } catch (e: Exception) {
            Log.i("infoteste", e.localizedMessage ?: "Error inesperado")
        }
        return null
    }
}
