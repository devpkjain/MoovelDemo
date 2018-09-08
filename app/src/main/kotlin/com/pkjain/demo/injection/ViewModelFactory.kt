package com.pkjain.demo.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.pkjain.R
import com.pkjain.demo.ui.rider.RiderListViewModel
import com.pkjain.demo.utils.loadFareInfo

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RiderListViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
//            db.postDao()

            return RiderListViewModel(activity.resources.loadFareInfo(R.raw.fare)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}