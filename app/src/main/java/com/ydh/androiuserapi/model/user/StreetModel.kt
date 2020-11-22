package com.ydh.androiuserapi.model.user

import com.google.gson.annotations.SerializedName

data class StreetModel(
        @SerializedName("number")
        val number: Int,
        @SerializedName("name")
        val name: String
)