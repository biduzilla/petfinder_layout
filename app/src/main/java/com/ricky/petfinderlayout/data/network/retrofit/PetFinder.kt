package com.ricky.petfinderlayout.data.network.retrofit

import com.ricky.petfinderlayout.data.network.models.ApiAnimal
import com.ricky.petfinderlayout.data.network.models.ApiAnimals
import com.ricky.petfinderlayout.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PetFinder {
    @GET(Constants.BASE_END_POINT)
    suspend fun getAnimals(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<ApiAnimals>

    @GET("${Constants.BASE_END_POINT}/{id}")
    suspend fun getAnimal(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<ApiAnimal>
}