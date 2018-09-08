package com.pkjain.demo.injection.component

import com.pkjain.demo.injection.module.NetworkModule
import com.pkjain.demo.ui.rider.RiderListViewModel
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

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}