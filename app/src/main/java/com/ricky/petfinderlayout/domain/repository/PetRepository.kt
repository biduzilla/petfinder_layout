package com.ricky.petfinderlayout.domain.repository

import com.ricky.petfinderlayout.data.network.models.ApiAnimal
import com.ricky.petfinderlayout.data.network.models.ApiAnimals
import retrofit2.Response

interface PetRepository {
    suspend fun getAnimails(token: String, page: Int = 0): Response<ApiAnimals>
    suspend fun getAnimal(id: Int, token: String): Response<ApiAnimal>
}