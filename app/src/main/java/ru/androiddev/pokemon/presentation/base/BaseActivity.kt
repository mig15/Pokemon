package ru.androiddev.pokemon.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject

abstract class BaseActivity<F : ViewModelProvider.Factory, V : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: F

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()

    protected fun setViewModel(klass: Class<V>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(klass)
    }
}