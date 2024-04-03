package com.ricky.petfinderlayout.domain.use_cases

import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.domain.repository.TokenRepository
import com.ricky.petfinderlayout.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetToken @Inject constructor(
    private val repository: TokenRepository,
    private val dataStoreUtil: DataStoreUtil,
) {
    operator fun invoke(): Flow<Resource<AccessToken>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.fetchAccessToken()

            if (result.isSuccessful) {
                result.body()?.let { token ->
                    dataStoreUtil.saveToken("${token.tokenType} ${token.accessToken}")
                    emit(Resource.Success(data = token))
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
