package com.ydh.androiuserapi.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.ApiClient
import com.ydh.androiuserapi.UserResponse
import com.ydh.androiuserapi.model.user.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoriteViewModel(val context: Context): ViewModel() {

    private val _users: MutableLiveData<List<UserModel>> by lazy {
        MutableLiveData<List<UserModel>>()
    }

    val users : LiveData<List<UserModel>>
        get() = _users

    fun setAllUser(){
        ApiClient.userApiService.getAllUser(20).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    _users.postValue(response.body()?.result)

                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                println(t)
                Toast.makeText(context, "$t", Toast.LENGTH_SHORT).show()
            }

        })
    }
}