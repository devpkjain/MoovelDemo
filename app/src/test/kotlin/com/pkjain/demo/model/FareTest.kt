package com.pkjain.demo.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *
 */
@RunWith(JUnit4::class)
class FareTest {
    private val desc = "Day Pass"
    private val price = 20.20f
    private val fare: Fare = Fare(desc, price)

    @Test
    fun getDescription() {
        assertEquals(desc, fare.description)
    }

    @Test
    fun getPrice() {
        assertEquals(price, fare.price)
    }
}