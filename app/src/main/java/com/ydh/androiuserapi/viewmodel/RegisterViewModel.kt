package com.ydh.androiuserapi.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.App
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.util.Email
import com.ydh.androiuserapi.util.Name
import com.ydh.androiuserapi.util.Password

class RegisterViewModel(context: Context): ViewModel() {
    private val prefs: UserSession by lazy {
        UserSession(App.instance)
    }
//    private lateinit var password: Password
//    lateinit var email:Email

    enum class RegisteredState {
        REGISTERED, UNREGISTERED
    }

    private val _isEmailValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isPasswordValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isNameValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    private val _isRegistered: MutableLiveData<RegisteredState> by lazy {
        MutableLiveData<RegisteredState>()
    }

    init {
        _isRegistered.value = RegisteredState.UNREGISTERED
    }


    val isRegistered: LiveData<RegisteredState>
        get() = _isRegistered

    val isPasswordValid: LiveData<Boolean>
        get() = _isPasswordValid

    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    val isNameValid: LiveData<Boolean>
        get() = _isNameValid

    fun completedForm(passwordEntered: String, emailEntered: String, nameEntered: String) {
        val email = Email(emailEntered)
        val password = Password(passwordEntered)
        val name = Name(nameEntered)
        if (password.isValidPassword && email.isValidEmail && name.isValidName) {
            _isRegistered.value = RegisteredState.REGISTERED
            prefs.registered = true
            prefs.email = emailEntered
            prefs.password = passwordEntered
            prefs.name = nameEntered
        }
    }
}