package com.pkjain.demo.model

import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *
 */
@RunWith(JUnit4::class)
class RiderTest {

    private val descAdult = "Day Pass"
    private val priceAdult = 20.20f
    private val fareAdult: Fare = Fare(descAdult, priceAdult)

    private val descChild = "Day Pass"
    private val priceChild = 20.20f
    private val fareChild: Fare = Fare(descChild, priceChild)

    private val descSenior = "Day Pass"
    private val priceSenior = 20.20f
    private val fareSenior: Fare = Fare(descSenior, priceSenior)

    private val subTextAdult: String? = null
    private val subTextChild: String = "Ages 8-17"
    private val subTextSenior: String = "Ages 60+"


    val adult: Fares = Fares(listOf(fareAdult), subTextAdult)
    val child: Fares = Fares(listOf(fareChild), subTextChild)
    val senior: Fares = Fares(listOf(fareSenior), subTextSenior)

    private val rider: Rider = Rider(adult, child, senior)

    @Test
    fun getAdult() {
        assertEquals(subTextAdult, rider.Adult.subtext)
        assertEquals(priceAdult, rider.Adult.fares.get(0).price)
        assertEquals(descAdult, rider.Adult.fares.get(0).description)
    }

    @Test
    fun getChild() {
        assertEquals(subTextChild, rider.Child.subtext)
        assertEquals(priceChild, rider.Child.fares.get(0).price)
        assertEquals(descChild, rider.Child.fares.get(0).description)
    }

    @Test
    fun getSenior() {
        assertEquals(subTextSenior, rider.Senior.subtext)
        assertEquals(priceSenior, rider.Senior.fares.get(0).price)
        assertEquals(descSenior, rider.Senior.fares.get(0).description)
    }
}