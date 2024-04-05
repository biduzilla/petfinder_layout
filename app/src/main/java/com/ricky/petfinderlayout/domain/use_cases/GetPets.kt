package com.ricky.petfinderlayout.domain.use_cases

import com.ricky.petfinderlayout.data.network.models.toPet
import com.ricky.petfinderlayout.domain.model.Pet
import com.ricky.petfinderlayout.domain.repository.PetRepository
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPets @Inject constructor(
    private val repository: PetRepository,
) {
    operator fun invoke(page: Int = 1): Flow<Resource<List<Pet>>> = flow {

        try {
            emit(Resource.Loading())

            val result = repository.getAnimails(
                page = page,
            )

            if (result.isSuccessful) {
                result.body()?.let { response ->
                    val pets = response.animals.map { it.toPet() }

                    pets.forEach { pet ->
                        pet.currentPage = response.pagination.currentPage
                    }

                    emit(Resource.Success(data = pets))
                } ?: run {
                    emit(Resource.Error("Error inesperado"))
                }
            } else {
                val errorBody = result.errorBody()?.string()
                emit(Resource.Error("Error Status ${result.code()} - Message ${result.message()} - Error Body $errorBody"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}