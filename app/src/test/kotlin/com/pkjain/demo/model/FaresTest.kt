package com.pkjain.demo.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *
 */
@RunWith(JUnit4::class)
class FaresTest {

    private val desc = "Day Pass"
    private val price = 20.20f
    private val fare: Fare = Fare(desc, price)
    private val subText = "subText"
    private val fares = Fares(listOf(fare), subText)
    @Test
    fun getFares() {
        assertEquals(fare, fares.fares.get(0))
    }

    @Test
    fun getSubtext() {
        assertEquals(subText, fares.subtext)
    }
}