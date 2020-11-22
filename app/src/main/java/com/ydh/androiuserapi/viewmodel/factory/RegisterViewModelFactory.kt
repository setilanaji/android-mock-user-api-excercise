package com.ydh.androiuserapi.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ydh.androiuserapi.viewmodel.LoginViewModel
import com.ydh.androiuserapi.viewmodel.RegisterViewModel

class RegisterViewModelFactory(private val context: Context?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(context!!) as T
    }
}