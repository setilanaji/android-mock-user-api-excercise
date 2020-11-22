package com.ydh.androiuserapi.util

import android.util.Patterns

class Email(private val email: String) {
    val isValidEmail: Boolean
        get() = validateEmail()

    private fun validateEmail(): Boolean = email.isNotEmpty() && validateEmailFormat()

    private fun validateEmailFormat(): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}