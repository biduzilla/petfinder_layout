package com.ricky.petfinderlayout.data.network.interceptors

import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.domain.use_cases.GetToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val getToken: GetToken
) : Interceptor {
    private var token = ""

    init {
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreUtil.getToken().collect {
                token = it
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.isSuccessful) {
            return response
        }

        response.close()

        if (token.isNotBlank()) {
            val newRequest = request
                .newBuilder()
                .addHeader("Authorization", token)
                .build()
            val responseToken = chain.proceed(newRequest)
            return if (responseToken.code == 401) {
                responseToken.close()
                return refreshToken(chain)
            } else {
                responseToken
            }
        } else {
            return refreshToken(chain)
        }
    }

    private fun refreshToken(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response: Response? = null

        runBlocking(Dispatchers.IO) {
            getToken()?.let {
                val token = "${it.tokenType} ${it.accessToken}"
                dataStoreUtil.saveToken(token)

                val newRequest = request
                    .newBuilder()
                    .header("Authorization", token)
                    .build()
                response = chain.proceed(newRequest)
            }
        }
        return response ?: chain.proceed(request)
    }
}