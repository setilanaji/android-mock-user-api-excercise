package com.ydh.androiuserapi.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.util.App
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.model.UserRegisterModel
import com.ydh.androiuserapi.util.Email
import com.ydh.androiuserapi.util.Password

class LoginViewModel(context: Context): ViewModel() {
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }

    enum class LoginState {
        LOGGED, UNLOGGED
    }

    private val _isEmailValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isPasswordValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isLogged: MutableLiveData<LoginState> by lazy {
        MutableLiveData<LoginState>()
    }

    init {
        _isLogged.value = LoginState.UNLOGGED
    }

    val isLogged: LiveData<LoginState>
        get() = _isLogged

    val isPasswordValid: LiveData<Boolean>
        get() = _isPasswordValid

    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    fun completedForm(passwordEntered: String, emailEntered: String) {
        val email = Email(emailEntered)
        val password = Password(passwordEntered)
        if (password.isValidPassword && email.isValidEmail) {
            if (isMatchLogin(emailEntered, passwordEntered)){
                _isLogged.value = LoginState.LOGGED
                prefs.loggedIn = true
            }
        }
    }

    private fun isMatchLogin(email: String, password: String): Boolean {
        var userRegistered = prefs.userRegisteredList
        if (userRegistered == null){
            userRegistered = ArrayList<UserRegisterModel>()
        }
        var saved = false
        userRegistered?.forEach { c ->
            if (c.email == email && c.password == password){
                saved = true
            }
        }
        return saved
    }
}