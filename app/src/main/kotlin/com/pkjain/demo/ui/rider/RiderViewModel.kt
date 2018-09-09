package com.pkjain.demo.ui.rider

import android.arch.lifecycle.MutableLiveData
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.RiderInfo

class RiderViewModel : BaseViewModel() {
    val riderInfo = MutableLiveData<RiderInfo>()

    fun bind(riderInfo: RiderInfo) {
        this.riderInfo.value = riderInfo
    }

    fun title() = riderInfo.value?.fareType?.name
    fun text() = riderInfo.value?.fares?.subtext
}