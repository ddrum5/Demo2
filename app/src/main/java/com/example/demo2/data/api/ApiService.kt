package com.example.demo2.data.api

import com.example.data.models.UserPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
     suspend fun getPage(@Query("page") page: Int): UserPageResponse
}