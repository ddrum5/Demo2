package com.example.demo2.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData

import com.example.demo2.base.BaseViewModel
import com.example.demo2.data.models.User
import com.example.demo2.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow


class HomeViewModel() : BaseViewModel() {

    private val users = MutableLiveData<MutableList<User>>()
    fun getUsers(): LiveData<MutableList<User>> {
        return users
    }
    suspend fun initData() {
        UserRepository().getData().also {
            users.value = it
        }
    }
    fun saveUserToDB(user: User) {
        userDao.insertAll(user)
    }

    private var apiInterface = UserRepository.instance

    @ExperimentalPagingApi
    suspend fun loadData(): Flow<PagingData<User>> {
        return apiInterface.loadData()
    }


}