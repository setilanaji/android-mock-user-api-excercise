package com.ydh.androiuserapi.model

sealed class User{
    data class Category(val category: String):User()
    data class Data(val user: UserModel): User()
}