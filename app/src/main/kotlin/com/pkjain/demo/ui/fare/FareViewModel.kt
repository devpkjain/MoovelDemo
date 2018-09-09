package com.pkjain.demo.ui.fare

import android.arch.lifecycle.MutableLiveData
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.Fare

class FareViewModel : BaseViewModel() {
    val fare = MutableLiveData<Fare>()
    private val text = MutableLiveData<String>()

    fun bind(fare: Fare) {
        this.fare.value = fare
        text.value = "$" + fare.price.toString()
    }

    fun title() = fare.value?.description

    fun text() = "$" + fare.value?.price
}