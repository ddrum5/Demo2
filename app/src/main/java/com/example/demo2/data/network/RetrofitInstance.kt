package com.example.demo2.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private const val baseUrl = "https://rickandmortyapi.com/api/"

        private val retrofit: Retrofit by lazy {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//            val client: OkHttpClient = OkHttpClient.Builder()
//                .addInterceptor(interceptor).build()

            Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        fun apiService(): APIService {
            return retrofit.create(APIService::class.java)
        }
    }


}