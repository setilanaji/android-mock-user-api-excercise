package com.ydh.androiuserapi.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ydh.androiuserapi.util.App
import com.ydh.androiuserapi.UserSession
import com.ydh.androiuserapi.model.UserRegisterModel
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
            if (!checkRegistered(emailEntered)){
               addNewUser(emailEntered, nameEntered, passwordEntered)
            }else{
                _isRegistered.value = RegisteredState.REGISTERED
            }
        }
    }

    private fun checkRegistered(email: String): Boolean {
        var userRegistered = prefs.userRegisteredList
        if (userRegistered == null){
            userRegistered = ArrayList<UserRegisterModel>()
        }
        var saved = false
        userRegistered.forEach { c ->
            println(c.toString())
            if (c.email == email){
                saved = true
                println("ketemu")
            }
        }

        return saved
    }
        private fun addNewUser(email: String, name: String, password: String){
//            val emailList: MutableList<String> = prefs.emailArray.toMutableList()
//            emailList.add(email)
//            prefs.emailArray = emailList.toTypedArray()

            var userRegistered = prefs.userRegisteredList
            if (userRegistered == null){
                userRegistered = ArrayList()
            }
            userRegistered.add(UserRegisterModel(name, email, password))
            prefs.userRegisteredList = userRegistered

        }
    
}