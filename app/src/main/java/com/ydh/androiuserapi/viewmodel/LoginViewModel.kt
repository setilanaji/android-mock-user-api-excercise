package com.ydh.androiuserapi.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.App
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.util.Email
import com.ydh.androiuserapi.util.Password

class LoginViewModel(context: Context): ViewModel() {
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }
//    private lateinit var password: Password
//    lateinit var email:Email

    enum class RegisteredState {
        LOGGED, UNLOGGED
    }

    private val _isEmailValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isPasswordValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isLogged: MutableLiveData<RegisteredState> by lazy {
        MutableLiveData<RegisteredState>()
    }

    init {
        _isLogged.value = RegisteredState.UNLOGGED

    }

    val isLogged: LiveData<RegisteredState>
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
                _isLogged.value = RegisteredState.LOGGED
                prefs.loggedIn = true
            }
        }
    }

    private fun isMatchLogin(email: String, password: String): Boolean = prefs.email == email && prefs.password == password
}