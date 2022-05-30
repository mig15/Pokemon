package ru.androiddev.pokemon.presentation

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.androiddev.pokemon.data.feature.pokemon.repository.PokemonRepositoryImpl
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var repository: PokemonRepositoryImpl

    @Test
    fun testEvent() {
        assertNotNull(repository)
    }
}