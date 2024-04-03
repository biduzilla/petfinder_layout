package com.ricky.petfinderlayout.domain.repository

import com.ricky.petfinderlayout.data.network.models.ApiAnimal
import com.ricky.petfinderlayout.data.network.models.ApiAnimals
import retrofit2.Response

interface PetRepository {
    suspend fun getAnimails(page: Int): Response<ApiAnimals>
    suspend fun getAnimal(id: Int): Response<ApiAnimal>
}