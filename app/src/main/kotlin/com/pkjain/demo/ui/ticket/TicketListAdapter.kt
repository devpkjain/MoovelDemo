package com.pkjain.demo.ui.ticket

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pkjain.R
import com.pkjain.databinding.ItemTicketBinding
import com.pkjain.demo.model.TicketInfo

class TicketListAdapter(val presenter: TicketListViewModel.Presenter) : RecyclerView.Adapter<TicketListAdapter.ViewHolder>() {
    private lateinit var itemList: List<TicketInfo>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketListAdapter.ViewHolder {
        val binding: ItemTicketBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_ticket, parent, false)
        return ViewHolder(binding, presenter)
    }

    override fun onBindViewHolder(holder: TicketListAdapter.ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return if (::itemList.isInitialized) itemList.size else 0
    }

    fun updatePostList(itemList: List<TicketInfo>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemTicketBinding, private val presenter: TicketListViewModel.Presenter) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = TicketViewModel()

        fun bind(item: TicketInfo) {
            viewModel.bind(item)
            binding.viewModel = viewModel
            binding.presenter = presenter
        }
    }
}