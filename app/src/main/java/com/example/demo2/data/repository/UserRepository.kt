package com.example.demo2.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.demo2.data.models.User
import com.example.demo2.data.network.APIService
import com.example.demo2.data.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class UserRepository {

    suspend fun getData(): MutableList<User> {
        var response = api.getPage(5)
        return response.results ?: mutableListOf();
    }

    private val api: APIService by lazy {
        RetrofitInstance.apiService()
    }

    @ExperimentalPagingApi
    suspend fun loadData(): Flow<PagingData<User>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                UserPagingSource(api)
            }).flow
    }

    companion object {
        val instance by lazy {
            UserRepository()
        }
    }

}