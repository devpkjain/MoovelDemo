package com.pkjain.demo.ui.rider

import android.arch.lifecycle.MutableLiveData
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.RiderInfo

class RiderViewModel : BaseViewModel() {
    private val title = MutableLiveData<String>()
    private val text = MutableLiveData<String>()

    fun bind(riderInfo: RiderInfo) {
        title.value = riderInfo.name
        text.value = riderInfo.fares.subtext
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getText(): MutableLiveData<String> {
        return text
    }
}