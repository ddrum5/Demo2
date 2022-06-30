package com.example.demo2.ui.favorite

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo2.base.BaseViewModel
import com.example.demo2.data.models.User

class FavoriteViewModel(context: Context) : BaseViewModel(context) {


     val users = MutableLiveData<MutableList<User>>()

    suspend fun init() {
//        userDao.getAll()?.also {
//            users.value = it
//

        userRepository.getData().also {
            users.value = it
        }
    }

    fun getUsers(): LiveData<MutableList<User>> {
        return users
    }





}