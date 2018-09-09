package com.pkjain.demo.ui.fare

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pkjain.R
import com.pkjain.databinding.ItemFareBinding
import com.pkjain.demo.model.Fare

class FareListAdapter(val presenter: FareListViewModel.Presenter) : RecyclerView.Adapter<FareListAdapter.ViewHolder>() {
    private lateinit var itemList: List<Fare>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FareListAdapter.ViewHolder {
        val binding: ItemFareBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_fare, parent, false)
        return ViewHolder(binding, presenter)
    }

    override fun onBindViewHolder(holder: FareListAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    fun updatePostList(itemList: List<Fare>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemFareBinding, private val presenter: FareListViewModel.Presenter) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FareViewModel()

        fun bind(item: Fare) {
            viewModel.bind(item)
            binding.viewModel = viewModel
            binding.presenter = presenter
        }
    }
}