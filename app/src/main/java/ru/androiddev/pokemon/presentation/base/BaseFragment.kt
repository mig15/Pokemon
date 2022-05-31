package ru.androiddev.pokemon.presentation.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<F : ViewModelProvider.Factory, V : BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModelFactory: F

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()

    protected fun setViewModel(klass: Class<V>) {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[klass]
    }
}