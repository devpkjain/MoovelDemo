package com.pkjain.demo.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.pkjain.R
import com.pkjain.demo.model.RiderInfo
import com.pkjain.demo.ui.fare.FareListViewModel
import com.pkjain.demo.utils.loadFareInfo

class FareViewModelFactory(private val activity: AppCompatActivity, private val presenter: FareListViewModel.Presenter, private val riderInfo: RiderInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FareListViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            val loadFareInfo = activity.resources.loadFareInfo(R.raw.fare)
            return FareListViewModel(loadFareInfo.get(0).fares.fares, presenter = presenter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}