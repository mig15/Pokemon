package ru.androiddev.pokemon.presentation

import android.app.Application
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import ru.androiddev.pokemon.presentation.di.DaggerApplicationComponent
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector, HasSupportFragmentInjector {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var supportFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .context(applicationContext)
            .build()
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentDispatchingAndroidInjector
    }
}