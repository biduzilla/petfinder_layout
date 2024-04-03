package com.ricky.petfinderlayout.data.network.repository

import com.ricky.petfinderlayout.data.network.models.ApiAnimal
import com.ricky.petfinderlayout.data.network.models.ApiAnimals
import com.ricky.petfinderlayout.data.network.retrofit.PetFinderApi
import com.ricky.petfinderlayout.domain.repository.PetRepository
import retrofit2.Response
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(private val api: PetFinderApi) : PetRepository {
    override suspend fun getAnimails(token: String, page: Int): Response<ApiAnimals> {
        return api.getAnimals(page = page, token = token)
    }

    override suspend fun getAnimal(id: Int, token: String): Response<ApiAnimal> {
        return api.getAnimal(id = id, token = token)
    }
}