package com.ydh.androiuserapi

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ydh.androiuserapi.model.UserRegisterModel

class UserSession(private val context: Context){
    companion object{
        const val SHARED_NAME = "com.ydh.androiduserapi"

        const val LOGGED_KEY = "LOGGED_KEY"
        const val REGISTERED_KEY = "REGISTERED_KEY"
        const val LOGIN_USER_EMAIL = "LOGIN_USER_EMAIL"
        const val LOGIN_USER_PASSWORD = "LOGIN_USER_PASSWORD"
        const val LOGIN_USER_NAME = "LOGIN_USER_NAME"
        const val REGISTERED_USER_EMAIL = "REGISTERED_USER_EMAIL"
        const val REGISTERED_USER_PASSWORD = "REGISTERED_USER_PASSWORD"
        const val REGISTERED_USER_NAME = "REGISTERED_USER_NAME"
        const val REGISTERED_USER_LIST = "REGISTERED_USER_LIST"


    }

    private val gson = Gson()

    private val prefs: SharedPreferences by lazy{
        context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE)
    }

    var loggedIn : Boolean
        get() = prefs.getBoolean(LOGGED_KEY, false)
        set(value) = prefs.edit { putBoolean(LOGGED_KEY, value) }

    var registered : Boolean
        get() = prefs.getBoolean(REGISTERED_KEY, false)
        set(value) = prefs.edit { putBoolean(REGISTERED_KEY, value) }

    var password : String?
        get() = prefs.getString(LOGIN_USER_PASSWORD, "")
        set(value) = prefs.edit { putString(LOGIN_USER_PASSWORD, value) }

    var email : String?
        get() = prefs.getString(LOGIN_USER_EMAIL, "")
        set(value) = prefs.edit { putString(LOGIN_USER_EMAIL, value) }

    var name : String?
        get() = prefs.getString(LOGIN_USER_NAME, "")
        set(value) = prefs.edit { putString(LOGIN_USER_NAME, value) }

    var emailArray: Array<String>
        get() = prefs.getStringSet(REGISTERED_USER_EMAIL, emptySet())?.toTypedArray()?: emptyArray()
        set(value) = prefs.edit { putStringSet(REGISTERED_USER_EMAIL, value.toSet()) }

//    var passwordArray: Array<String>
//        get() = prefs.getStringSet(REGISTERED_USER_PASSWORD, emptySet())?.toTypedArray()?: emptyArray()
//        set(value) = prefs.edit { putStringSet(REGISTERED_USER_PASSWORD, value.toSet()) }
//
//    var nameArray: Array<String>
//        get() = prefs.getStringSet(REGISTERED_USER_NAME, emptySet())?.toTypedArray()?: emptyArray()
//        set(value) = prefs.edit { putStringSet(REGISTERED_USER_NAME, value.toSet()) }
//
//    fun isRegisteredEmail(email: String): Boolean{
//        val email: MutableList<String> = this.emailArray.toMutableList()
//        var saved = false
//        email.forEach { c ->
//            if (c == "$email"){
//                saved = true
//            }
//        }
//        return saved
//    }

//    fun isRegisteredPassword(password: String): Boolean{
//        val password: MutableList<String> = this.passwordArray.toMutableList()
//        var saved = false
//        password.forEach { c ->
//            if (c == "$password"){
//                saved = true
//            }
//        }
//        return saved
//    }


    var userRegisteredList: ArrayList<UserRegisterModel>?
        get() {
            val jsonString = prefs.getString(REGISTERED_USER_LIST, null) ?: return null
            return gson.fromJson(jsonString, object : TypeToken<ArrayList<UserRegisterModel>>() {}.type)
        }
        set(value) = prefs.edit { putString(REGISTERED_USER_LIST, gson.toJson(value)) }


}