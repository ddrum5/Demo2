package com.example.data.models

import com.example.demo2.models.User
import com.google.gson.annotations.SerializedName

data class UserPageResponse(

    @SerializedName("info")
    var info: PageInfo? = null,
    var results: MutableList<User>? = null

)


