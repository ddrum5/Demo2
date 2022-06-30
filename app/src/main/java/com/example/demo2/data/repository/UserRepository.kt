package com.example.demo2.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.demo2.data.models.User
import com.example.demo2.data.api.ApiService
import com.example.demo2.data.api.RetrofitInstance
import com.example.demo2.data.db.UserDatabase
import kotlinx.coroutines.flow.Flow

class UserRepository (context: Context) {

    private val api: ApiService by lazy {
        RetrofitInstance.apiService()
    }
    private val database: UserDatabase by lazy {
        UserDatabase.getInstance(context)
    }

    @ExperimentalPagingApi
    suspend fun loadData(): Flow<PagingData<User>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = {
                UserPagingSource(api)
            }).flow
    }
    @ExperimentalPagingApi
    fun loadDataDB(): Flow<PagingData<User>> {
        val pagingSourceFactory ={
            database.userDao().selectUser()
        }
        return Pager(
            config =PagingConfig(pageSize = 1, maxSize = 200),
            remoteMediator = UserRemoteMediator(
                database = database, apiService = api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }



    suspend fun getData(): MutableList<User> {
        var response = api.getPage(5)
        return response.results ?: mutableListOf();
    }



}