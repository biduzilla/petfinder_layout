package com.ricky.petfinderlayout.domain.use_cases

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.data.network.models.ApiAnimals
import com.ricky.petfinderlayout.domain.repository.PetRepository
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPets @Inject constructor(
    private val repository: PetRepository,
    private val dataStore: DataStoreUtil
) {
    operator fun invoke(page: Int = 0): Flow<Resource<ApiAnimals>> = flow {
        var token by mutableStateOf("")

        dataStore.getToken().collect {
            token = it
        }

        try {
            emit(Resource.Loading())

            val result = repository.getAnimails(
                page = page,
                token = token
            )

            if (result.isSuccessful) {
                result.body()?.let {
                    emit(Resource.Success(data = it))
                } ?: run {
                    emit(Resource.Error("Error inesperado"))
                }
            } else {
                emit(Resource.Error(message = "Error Status ${result.code()} - Message ${result.message()} - Body ${result.body()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}