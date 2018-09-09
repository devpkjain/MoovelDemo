package com.pkjain.demo.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


/**
 *TicketInfoTest
 */
@RunWith(MockitoJUnitRunner::class)
class TicketInfoTest {

    private val fareType: FareType = FareType.Senior
    private val initialCount: Int = 1
    private val fare: Fare = Fare("Day Pass", 20.20f)
    private val ticketTotalText: String = "Buy %s tickets - \$%s"
    private lateinit var ticketInfo: TicketInfo

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline() }

        ticketInfo = TicketInfo(fareType, fare, initialCount, ticketTotalText)
    }

    @Test
    fun getCount() {
        Assert.assertEquals(initialCount, ticketInfo.count.value)
    }

    @Test
    fun getCost() {
        Assert.assertEquals(initialCount * fare.price, ticketInfo.cost.value)
    }

    @Test
    fun getTicketTotal() {
        Assert.assertEquals(ticketTotalText.format(initialCount, initialCount * fare.price), ticketInfo.ticketTotal.value)
    }

    @Test
    fun getFareType() {
        Assert.assertEquals(fareType, ticketInfo.fareType)
    }

    @Test
    fun getFare() {
        Assert.assertEquals(fare, ticketInfo.fare)
    }

}