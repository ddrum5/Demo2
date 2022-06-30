package com.example.demo2.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.demo2.data.models.User

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM User")
    fun getAll(): MutableList<User>


    @Query("DELETE FROM User")
    fun deleteAllUser()

    @Query("select * from User ORDER BY `index` ASC ")
    fun selectUser(): PagingSource<Int, User>

    @Query("select * from User ORDER BY `index` ASC LIMIT 1")
    fun firstUser(): User?

}