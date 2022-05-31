package ru.androiddev.pokemon.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection

abstract class BaseActivity<F : ViewModelProvider.Factory, V : BaseViewModel> : AppCompatActivity() {

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()
}