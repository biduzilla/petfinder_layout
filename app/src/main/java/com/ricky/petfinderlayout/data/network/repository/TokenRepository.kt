package com.ricky.petfinderlayout.data.network.repository

import com.ricky.petfinderlayout.data.network.models.AccessToken
import retrofit2.Response

interface TokenRepository {
    suspend fun fetchAccessToken(): Response<AccessToken>
}