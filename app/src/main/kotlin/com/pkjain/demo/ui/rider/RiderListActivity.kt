package com.pkjain.demo.ui.rider

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import com.pkjain.R
import com.pkjain.databinding.ActivityRiderListBinding
import com.pkjain.demo.injection.RiderViewModelFactory
import com.pkjain.demo.model.RiderInfo
import com.pkjain.demo.ui.fare.FareListActivity


class RiderListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiderListBinding
    private lateinit var viewModel: RiderListViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.apply {
            setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.header)))
            setTitle(Html.fromHtml("<font color='#8A8A8A'>Select Rider</font>"))
        }


        binding = DataBindingUtil.setContentView(this, R.layout.activity_rider_list)
        binding.riderList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val context = this
        viewModel = ViewModelProviders.of(context, RiderViewModelFactory(this,
                object : RiderListViewModel.Presenter {
                    override fun onClick(riderInfo: RiderInfo) {
                        startActivity(FareListActivity.createIntent(context, riderInfo))
                    }
                }
        )).get(RiderListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
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