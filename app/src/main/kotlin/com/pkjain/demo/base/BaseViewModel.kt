package com.pkjain.demo.base

import android.arch.lifecycle.ViewModel
import com.pkjain.demo.injection.component.DaggerViewModelInjector
import com.pkjain.demo.injection.component.ViewModelInjector
import com.pkjain.demo.injection.module.NetworkModule
import com.pkjain.demo.ui.fare.FareListViewModel
import com.pkjain.demo.ui.rider.RiderListViewModel
import com.pkjain.demo.ui.ticket.TicketListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is RiderListViewModel -> injector.inject(this)
            is FareListViewModel -> injector.inject(this)
            is TicketListViewModel -> injector.inject(this)
        }
    }
}