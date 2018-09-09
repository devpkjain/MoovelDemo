package com.pkjain.demo.ui.rider

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pkjain.R
import com.pkjain.databinding.ItemRiderBinding
import com.pkjain.demo.model.RiderInfo

class RiderListAdapter(val presenter: RiderListViewModel.Presenter) : RecyclerView.Adapter<RiderListAdapter.ViewHolder>() {
    private lateinit var itemList: List<RiderInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiderListAdapter.ViewHolder {
        val binding: ItemRiderBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_rider, parent, false)
        return ViewHolder(binding, presenter)
    }

    override fun onBindViewHolder(holder: RiderListAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    fun updatePostList(itemList: List<RiderInfo>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRiderBinding, private val presenter: RiderListViewModel.Presenter) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = RiderViewModel()

        fun bind(item: RiderInfo) {
            viewModel.bind(item)
            binding.viewModel = viewModel
            binding.presenter = presenter
        }
    }
}