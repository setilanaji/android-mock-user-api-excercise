package com.ydh.androiuserapi.util


class Password(private val password: String) {
    private val minSize = 8
    private val maxSIze = 20
//    private val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}\$")
    val isValidPassword: Boolean
        get() = validatePassword()

    private fun validatePassword(): Boolean = password.isNotEmpty() && validatePasswordFormat()

//    fun validatePassword(password: String): Boolean = password.isNotEmpty() && validatePasswordFormat()

    private fun validatePasswordFormat(): Boolean = password.length in minSize..maxSIze
//            && password.matches(regex)

//    fun isValidConfirmPassword(confirmPassword: String): Boolean {
//        return validateConfirmPassword(confirmPassword)
//    }
//
//    fun validateMatchNewPassword(confirmPassword: String): Boolean {
//        return password == confirmPassword
//    }
//
//    fun validateConfirmPassword(confirmPassword: String): Boolean {
//        return !confirmPassword.isEmpty() && validateMatchNewPassword(confirmPassword)
//    }
}
