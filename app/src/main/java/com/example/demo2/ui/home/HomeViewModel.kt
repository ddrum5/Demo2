package com.example.demo2.ui.home


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData

import com.example.demo2.base.BaseViewModel
import com.example.demo2.data.models.User
import com.example.demo2.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow


class HomeViewModel(context: Context) : BaseViewModel(context) {

    private val users = MutableLiveData<MutableList<User>>()



    @ExperimentalPagingApi
    suspend fun loadData(): Flow<PagingData<User>> {
        return userRepository.loadData()
    }




}