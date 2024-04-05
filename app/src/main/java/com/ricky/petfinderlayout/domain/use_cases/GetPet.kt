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

class GetPet @Inject constructor(private val repository: PetRepository) {

    operator fun invoke(petId: Int): Flow<Resource<Pet>> = flow {
        try {
            emit(Resource.Loading())

            repository.getAnimal(petId).let { result ->
                if (result.isSuccessful) {
                    result.body()?.let { response ->
                        val pet = response.animal.toPet()
                        emit(Resource.Success(data = pet))
                    } ?: run {
                        emit(Resource.Error("Error inesperado"))
                    }
                } else {
                    val errorBody = result.errorBody()?.string()
                    emit(Resource.Error("Error Body $errorBody"))
                }
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}