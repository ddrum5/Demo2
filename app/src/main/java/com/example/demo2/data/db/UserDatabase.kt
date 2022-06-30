package com.example.demo2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.codelabs.paging.db.RemoteKeys
import com.example.android.codelabs.paging.db.RemoteKeysDao
import com.example.demo2.data.models.User

@Database(
    entities = [User::class, RemoteKeys::class],
    version = 1,
    exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        private const val DBNAME = "UserDB"

        @Volatile
        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
            instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                UserDatabase::class.java, DBNAME
            )
                .allowMainThreadQueries()
                .build()

    }
}
