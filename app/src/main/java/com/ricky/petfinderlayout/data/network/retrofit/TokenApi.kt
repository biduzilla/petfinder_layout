package com.ricky.petfinderlayout.data.network.retrofit

import com.ricky.petfinderlayout.data.network.models.AccessToken
import com.ricky.petfinderlayout.utils.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenApi {
    @POST(Constants.AUTH_END_POINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(Constants.CLIENT_ID) clientId: String = Constants.API_KEY,
        @Field(Constants.CLIENT_SECRET) clientSecret: String = Constants.SECRET_KEY,
        @Field(Constants.GRANT_TYPE) grantType: String = Constants.CLIENT_CREDENTIALS
    ): Response<AccessToken>
}