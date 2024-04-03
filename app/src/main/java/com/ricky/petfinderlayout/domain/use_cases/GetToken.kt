package com.ricky.petfinderlayout.domain.use_cases

import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.data.network.repository.TokenRepository
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetToken @Inject constructor(private val repository: TokenRepository) {
    operator fun invoke(): Flow<Resource<AccessToken>> = flow {
        try {
            emit(Resource.Loading())
            repository.fetchAccessToken()?.let { token ->
                emit(Resource.Success(data = token))
            } ?: run {
                emit(Resource.Error(message = "Error ao obter token"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error("Cheque sua conexão com a internet"))
        }
    }
}
