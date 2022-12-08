package com.app.bankmisr.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()
}