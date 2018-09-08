package com.pkjain.demo.ui.fare

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import com.pkjain.R
import com.pkjain.databinding.ActivityFareListBinding
import com.pkjain.demo.injection.FareViewModelFactory
import com.pkjain.demo.model.Fare
import com.pkjain.demo.model.RiderInfo
import com.pkjain.demo.model.TicketInfo
import com.pkjain.demo.ui.ticket.ConfirmationActivity

class FareListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFareListBinding
    private lateinit var viewModel: FareListViewModel
    private var errorSnackbar: Snackbar? = null
    private var riderInfo: RiderInfo? = null


    companion object {
        val RIDER_INFO: String = "RIDER_INFO"

        @JvmStatic
        fun createIntent(context: Context, riderInfo: RiderInfo): Intent {
            val bundle = Bundle()
            bundle.putParcelable(RIDER_INFO, riderInfo)
            return Intent(context, FareListActivity::class.java).putExtras(bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.header)))
            setTitle(Html.fromHtml("<font color='#8A8A8A'>Select Fare</font>"))
        }

        riderInfo = intent.getParcelableExtra(RIDER_INFO)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fare_list)
        binding.fareList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val context = this
        riderInfo?.let {
            viewModel = ViewModelProviders.of(this, FareViewModelFactory(this,
                    object : FareListViewModel.Presenter {
                        override fun onClick(fare: Fare) {
                            startActivity(ConfirmationActivity.createIntent(
                                    context, TicketInfo(it.fareType, fare, 1, getString(R.string.ticket_total))))
                        }
                    }, it)
            ).get(FareListViewModel::class.java)

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
}