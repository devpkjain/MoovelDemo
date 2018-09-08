package com.pkjain.demo.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.pkjain.R
import com.pkjain.demo.model.FareType
import com.pkjain.demo.model.TicketInfo
import com.pkjain.demo.ui.rider.RiderListViewModel
import com.pkjain.demo.ui.ticket.TicketListViewModel
import com.pkjain.demo.utils.loadFareInfo

class TicketViewModelFactory(private val activity: AppCompatActivity, private val presenter: TicketListViewModel.Presenter, val ticketInfo: TicketInfo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicketListViewModel::class.java)) {
            return TicketListViewModel(listOf(ticketInfo),
                    presenter = presenter) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}