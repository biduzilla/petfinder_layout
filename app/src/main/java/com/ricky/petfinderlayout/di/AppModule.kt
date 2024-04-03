package com.ricky.petfinderlayout.di

import android.content.Context
import com.ricky.petfinderlayout.data.local.DataStoreUtil
import com.ricky.petfinderlayout.data.network.repository.TokenRepository
import com.ricky.petfinderlayout.data.network.repository.impl.TokenRepositoryImpl
import com.ricky.petfinderlayout.data.network.retrofit.TokenApi
import com.ricky.petfinderlayout.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Singleton
    @Provides
    fun provideTokenApi(): TokenApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TokenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTokenRepository(
        api: TokenApi,
        dataStoreUtil: DataStoreUtil
    ): TokenRepository {
        return TokenRepositoryImpl(dataStoreUtil = dataStoreUtil, api = api)
    }

}