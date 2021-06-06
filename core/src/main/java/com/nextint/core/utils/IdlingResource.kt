package com.nextint.core.utils

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE: String = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)
    /*Increment digunakan untuk menambahkan state loading dan decrement untuk mengurangi state loading-nya*/
    fun increment() = espressoTestIdlingResource.increment()

}