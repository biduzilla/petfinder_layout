package com.ricky.petfinderlayout.data.network.repository

import com.ricky.petfinderlayout.data.network.models.AccessToken

interface TokenRepository {
    suspend fun fetchAccessToken(): AccessToken?
}