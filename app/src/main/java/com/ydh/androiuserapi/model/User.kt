package com.ydh.androiuserapi.model

import com.ydh.androiuserapi.model.user.UserModel

sealed class User{
    data class Category(val category: String):User()
    data class Data(val user: UserModel): User()
}