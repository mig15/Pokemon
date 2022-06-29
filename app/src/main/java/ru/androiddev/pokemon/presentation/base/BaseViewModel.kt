package ru.androiddev.pokemon.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel() {

    val superJob = SupervisorJob()
    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()
}