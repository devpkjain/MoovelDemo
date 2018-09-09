package com.pkjain.demo.ui.ticket

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.pkjain.R
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.PostDao
import com.pkjain.demo.model.TicketInfo
import com.pkjain.demo.network.PostApi
import javax.inject.Inject

class TicketListViewModel(private val itemList: List<TicketInfo>,
                          private val postDao: PostDao? = null,
                          val presenter: Presenter) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val ticketListAdapter: TicketListAdapter = TicketListAdapter(presenter)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
    }

    private fun loadPosts() {
        onRetrievePostListSuccess(itemList);
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(itemList: List<TicketInfo>) {
        ticketListAdapter.updatePostList(itemList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    interface Presenter {

        fun onClick(ticketInfo: TicketInfo)
    }

    fun ticketInfo() = itemList.get(0)

}