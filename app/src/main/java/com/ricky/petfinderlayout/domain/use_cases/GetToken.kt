package com.ricky.petfinderlayout.domain.use_cases

import android.util.Log
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.domain.repository.TokenRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repository: TokenRepository,
    private val dataStoreUtil: DataStoreUtil,
) {
    suspend operator fun invoke(): AccessToken? {
        try {
            val result = repository.fetchAccessToken()

            if (result.isSuccessful) {
                result.body()?.let { token ->
                    return token
                } ?: run {
                    Log.i("infoteste", "Error inesperado ao tentar pegar token")
                }
            } else {
                Log.i(
                    "infoteste",
                    "Error Status ${result.code()} - Message ${result.message()} - Body ${result.body()}"
                )
            }

        } catch (e: HttpException) {
            Log.i("infoteste", e.localizedMessage ?: "Error inesperado")
        } catch (e: IOException) {
            Log.i("infoteste", "Cheque sua conexão com a internet")
        }
        return null
    }
}
