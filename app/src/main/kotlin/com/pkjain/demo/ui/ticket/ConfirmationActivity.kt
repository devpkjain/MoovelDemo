package com.pkjain.demo.ui.ticket

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import com.pkjain.R
import com.pkjain.databinding.ActivityConfirmationBinding
import com.pkjain.demo.injection.TicketViewModelFactory
import com.pkjain.demo.model.TicketInfo

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConfirmationBinding
    private lateinit var viewModel: TicketListViewModel
    private var errorSnackbar: Snackbar? = null
    private var ticketInfo: TicketInfo? = null
    private var isComplete = false
    companion object {
        val TICKET_INFO: String = "TICKET_INFO"

        @JvmStatic
        fun createIntent(context: Context, ticketInfo: TicketInfo): Intent {
            val bundle = Bundle()
            bundle.putParcelable(TICKET_INFO, ticketInfo)
            return Intent(context, ConfirmationActivity::class.java).putExtras(bundle)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.header)))
            setTitle(Html.fromHtml("<font color='#8A8A8A'>Confirm Selection</font>"))
        }
        ticketInfo = intent.getParcelableExtra(TICKET_INFO)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_confirmation)
        binding.ticketList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ticketInfo?.let {
            viewModel = ViewModelProviders.of(this, TicketViewModelFactory(this,
                    object : TicketListViewModel.Presenter {
                        override fun onClick(ticketInfo: TicketInfo) {
                            isComplete = true
                            Snackbar.make(binding.root, "Checkout process completed", Snackbar.LENGTH_LONG)
                                    .addCallback(object : Snackbar.Callback(){
                                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                            super.onDismissed(transientBottomBar, event)
                                            finishAffinity()
                                        }
                                    })
                                    .show()
                        }
                    }, it)
            ).get(TicketListViewModel::class.java)
            viewModel.errorMessage.observe(this, Observer { errorMessage ->
                if (errorMessage != null) showError(errorMessage) else hideError()
            })
            binding.viewModel = viewModel
        }
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onBackPressed() {
        if(isComplete) {
            finishAffinity()
        }else {
            super.onBackPressed()
        }
    }
}