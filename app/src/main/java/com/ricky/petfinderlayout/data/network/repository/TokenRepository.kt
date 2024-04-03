package com.ricky.petfinderlayout.data.network.repository

import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun fetchAccessToken(): AccessToken?
}