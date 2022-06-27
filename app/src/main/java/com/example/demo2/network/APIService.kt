package com.example.demo2.network

import com.example.data.models.UserPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("character")
     suspend fun getPage(@Query("page") page: Int): UserPageResponse
}