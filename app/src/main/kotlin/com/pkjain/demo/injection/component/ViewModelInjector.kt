package com.pkjain.demo.injection.component

import com.pkjain.demo.injection.module.NetworkModule
import com.pkjain.demo.ui.fare.FareListViewModel
import com.pkjain.demo.ui.fare.FareViewModel
import com.pkjain.demo.ui.rider.RiderListViewModel
import com.pkjain.demo.ui.ticket.TicketListViewModel
import com.pkjain.demo.ui.ticket.TicketViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified RiderListViewModel.
     * @param riderListViewModel RiderListViewModel in which to inject the dependencies
     */
    fun inject(riderListViewModel: RiderListViewModel)
    fun inject(fareListViewModel: FareListViewModel)
    fun inject(ticketListViewModel: TicketListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}