package com.pkjain.demo.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.pkjain.R
import com.pkjain.demo.ui.rider.RiderListViewModel
import com.pkjain.demo.utils.loadFareInfo

class RiderViewModelFactory(private val activity: AppCompatActivity, private val presenter: RiderListViewModel.Presenter) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RiderListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RiderListViewModel(activity.resources.loadFareInfo(R.raw.fare), presenter = presenter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}