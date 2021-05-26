package com.nextint.core.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private val RESOURCE: String = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    /*Increment digunakan untuk menambahkan state loading dan decrement untuk mengurangi state loading-nya*/
    fun increment() = espressoTestIdlingResource.increment()

    fun decrement() = espressoTestIdlingResource.decrement()

    fun getEspressoIdlingResource(): IdlingResource = espressoTestIdlingResource
}