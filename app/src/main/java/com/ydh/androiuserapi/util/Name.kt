package com.ydh.androiuserapi.util

class Name(private val name: String) {
    private val minSize = 2
    private val maxSIze = 7
//    private val regex = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}\$")
    val isValidName: Boolean
        get() = validateName()

    private fun validateName(): Boolean = name.isNotEmpty() && validateNameFormat()

//    fun validatePassword(name: String): Boolean = name.isNotEmpty() && validatePasswordFormat()

    private fun validateNameFormat(): Boolean = name.length in minSize..maxSIze

}