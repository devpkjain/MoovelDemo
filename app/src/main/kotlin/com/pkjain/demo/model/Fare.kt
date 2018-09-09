package com.pkjain.demo.model

import android.arch.lifecycle.MutableLiveData
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 *This class defines fare info
 */


@Parcelize
data class TicketInfo(val fareType: FareType, val fare: Fare, private val initialCount: Int, private val ticketTotalText: String) : Parcelable {
    val count = MutableLiveData<Int>()
    val cost = MutableLiveData<Float>()
    val ticketTotal = MutableLiveData<String>()

    init {
        count.observeForever { t: Int? ->
            t?.let {
                cost.value = it * fare.price
                ticketTotal.postValue(ticketTotalText.format(count.value, cost.value))
            }
        }
        count.value = initialCount
    }
}

@Parcelize
data class RiderInfo(val fareType: FareType, val fares: Fares) : Parcelable

@Parcelize
data class Rider(val Adult: Fares, val Child: Fares, val Senior: Fares) : Parcelable

@Parcelize
data class Fares(val fares: List<Fare>, val subtext: String?) : Parcelable

@Parcelize
data class Fare(val description: String, val price: Float) : Parcelable

enum class FareType {
    Adult,
    Child,
    Senior
}
