package com.example.demo2.repository

import com.example.demo2.models.User
import com.example.demo2.network.APIService
import com.example.demo2.network.RetrofitInstance

class UserRepository {
    private var api = RetrofitInstance.apiService()


     suspend fun getData(): MutableList<User> {

        var response = api.getPage(5)

        return response.results ?: mutableListOf();
    }
}