package com.ydh.androiuserapi.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.model.user.UserModel
import com.ydh.androiuserapi.util.App

class ProfileViewModel(val context: Context): ViewModel() {
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    private val _email: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val email : LiveData<String>
        get() = _email

    private val _name: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val name : LiveData<String>
        get() = _name

    private val _password: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val password : LiveData<String>
        get() = _password


    fun logOut(){
        prefs.loggedIn = false
    }


    fun setData(){
        _email.postValue(prefs.email)
        _name.postValue(prefs.name)
        _password.postValue(prefs.password)
    }



}