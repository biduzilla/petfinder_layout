package com.ricky.petfinderlayout.data.network.repository.impl

import android.util.Log
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.data.network.repository.TokenRepository
import com.ricky.petfinderlayout.data.network.retrofit.TokenApi
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val api: TokenApi
) : TokenRepository {
    override suspend fun fetchAccessToken(): AccessToken? {
        val response = api.getAuthToken()
        return if (response.isSuccessful) {
            response.body()?.let { token ->
                dataStoreUtil.saveToken("${token.tokenType} ${token.accessToken}")
                return token
            }
        } else {
            Log.i(
                "infoteste",
                "fetchAccessToken: ${response.code()} - ${response.message()} - ${response.body()}"
            )
            null
        }
    }
}