package com.pkjain.demo.ui.ticket

import android.arch.lifecycle.MutableLiveData
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.TicketInfo

class TicketViewModel : BaseViewModel() {
    val ticketInfo = MutableLiveData<TicketInfo>()

    fun bind(ticketInfo: TicketInfo) {
        this.ticketInfo.value = ticketInfo
    }

    fun title() = ticketInfo.value?.fareType.toString()

    fun text() = ticketInfo.value?.fare?.description

    fun count() = ticketInfo.value?.count

    fun countPlus() {
        ticketInfo.value?.count?.apply { postValue(this.value?.plus(1)) }
    }

    fun countMinus() {
        ticketInfo.value?.count?.apply {
            if(this.value?.minus(1)!=0){
                postValue(this.value?.minus(1))
                countMinusEnabled.postValue((this.value?.minus(1) != 0))
            }
        }
    }

    val countMinusEnabled = MutableLiveData<Boolean>()

    fun ticketTotal() = ticketInfo.value?.ticketTotal
}