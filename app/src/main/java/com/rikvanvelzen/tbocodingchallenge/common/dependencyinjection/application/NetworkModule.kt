package com.rikvanvelzen.tbocodingchallenge.common.dependencyinjection.application

import com.rikvanvelzen.tbocodingchallenge.data.api.BitcoinPriceIndexApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL_COIN_DESK = "https://api.coindesk.com/v1/bpi/"

@Module
class NetworkModule {

    @Provides
    fun getBitcoinPriceIndexService(retrofit: Retrofit): BitcoinPriceIndexApi = retrofit.create(BitcoinPriceIndexApi::class.java)

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL_COIN_DESK)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }
}