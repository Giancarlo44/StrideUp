package com.example.strideup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginPrincipalViewModel : ViewModel() {
    private val _navigateTo = MutableLiveData<String?>()
    val navigateTo: LiveData<String?> get() = _navigateTo

    fun onSiggaLoginClicked() {
        _navigateTo.value = "LoginSigga"
    }

    fun onMicrosoftLoginClicked() {
        _navigateTo.value = "LoginMicrosoft"
    }

    fun onNavigationCompleted() {
        _navigateTo.value = null
    }
}

