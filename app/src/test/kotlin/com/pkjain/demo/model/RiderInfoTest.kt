package com.pkjain.demo.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *
 */
@RunWith(JUnit4::class)
class RiderInfoTest {

    private val desc = "Day Pass"
    private val price = 20.20f
    private val fare: Fare = Fare(desc, price)
    private val subText = "subText"
    private val fares = Fares(listOf(fare), subText)
    private val fareType: FareType = FareType.Senior


    private val riderInfo: RiderInfo = RiderInfo(fareType, fares)
    @Test
    fun getFareType() {
        assertEquals(fareType, riderInfo.fareType)
    }

    @Test
    fun getFares() {
        assertEquals(subText, riderInfo.fares.subtext)
        assertEquals(fare.description, riderInfo.fares.fares.get(0).description)
        assertEquals(fare.price, riderInfo.fares.fares.get(0).price)
    }
}