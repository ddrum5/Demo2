package com.example.demo2.data.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: String,
    @ColumnInfo var name: String? = null,
    @ColumnInfo var gender: String? = null,
    @ColumnInfo var image: String? = null
)
{
    @ColumnInfo var index: Int ? = null
}
