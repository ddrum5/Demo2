package com.example.demo2.ui.remote_mediator



import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData

import com.example.demo2.base.BaseViewModel
import com.example.demo2.data.models.User
import kotlinx.coroutines.flow.Flow


class RemoteMediatorViewModel(context: Context) : BaseViewModel(context) {

    @ExperimentalPagingApi
    fun loadData(): Flow<PagingData<User>> {
        return userRepository.loadDataDB()
    }

}