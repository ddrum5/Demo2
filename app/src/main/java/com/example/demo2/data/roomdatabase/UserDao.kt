package com.example.demo2.data.roomdatabase

import androidx.room.*
import com.example.demo2.data.models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAll(): MutableList<User>

    @Query("SELECT * FROM User WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): MutableList<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}