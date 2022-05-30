package ru.androiddev.pokemon.presentation.extension

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.addFragment(fragment: Fragment, @IdRes container: Int) {
    supportFragmentManager
        .beginTransaction()
        .add(container, fragment, fragment.javaClass.name)
        .commit()
}