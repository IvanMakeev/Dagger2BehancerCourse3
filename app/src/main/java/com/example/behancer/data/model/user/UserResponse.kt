package com.example.behancer.data.model.user

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("user")
    var user: User
)