package ru.androiddev.pokemon.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val loadingLiveData = MutableLiveData<Boolean>()
}