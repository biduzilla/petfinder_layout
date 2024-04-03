package com.ricky.petfinderlayout.data.network.repository

import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.data.network.retrofit.TokenApi
import com.ricky.petfinderlayout.domain.repository.TokenRepository
import retrofit2.Response
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val api: TokenApi
) : TokenRepository {
    override suspend fun fetchAccessToken(): Response<AccessToken> {
        return api.getAuthToken()
    }
}