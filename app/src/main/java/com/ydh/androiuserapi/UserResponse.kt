package com.ydh.androiuserapi

import com.google.gson.annotations.SerializedName
import com.ydh.androiuserapi.model.user.UserModel

data class UserResponse(
    @SerializedName("results")
    val result: List<UserModel>
)