package com.example.cryptoapp.di

import com.example.cryptoapp.common.Constants.BASE_URL
import com.example.cryptoapp.data.remote.PaprikaApi
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // All the dependencies in the module live as long as the application does (SingletonComponent)
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi() : PaprikaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PaprikaApi::class.java)
    }
    @Provides
    @Singleton
    fun provideCoinRepository(api: PaprikaApi) : CoinRepository{

        return CoinRepositoryImpl(api)
    }
}