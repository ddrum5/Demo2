package com.example.demo2.data.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo2.data.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabaseInstance : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private const val dataBaseName = "UserDB"
        private var databaseInstance: RoomDatabaseInstance? = null

        fun getInstance(context: Context): RoomDatabaseInstance? {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context,
                    RoomDatabaseInstance::class.java, dataBaseName
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance
        }


    }
}
