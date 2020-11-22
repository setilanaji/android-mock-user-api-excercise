package com.ydh.androiuserapi.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ydh.androiuserapi.viewmodel.FavoriteViewModel
import com.ydh.androiuserapi.viewmodel.ProfileViewModel

class ProfileViewModelFactory( private val context: Context?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(context!!) as T
    }

}