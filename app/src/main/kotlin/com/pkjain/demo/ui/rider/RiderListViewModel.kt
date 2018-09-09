package com.pkjain.demo.ui.rider

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.pkjain.R
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.PostDao
import com.pkjain.demo.model.RiderInfo
import com.pkjain.demo.network.PostApi
import javax.inject.Inject

class RiderListViewModel(private val itemList: List<RiderInfo>, private val postDao: PostDao? = null, val presenter: RiderListViewModel.Presenter) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val riderListAdapter: RiderListAdapter = RiderListAdapter(presenter)

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

    private fun onRetrievePostListSuccess(itemList: List<RiderInfo>) {
        riderListAdapter.updatePostList(itemList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    interface Presenter {

        fun onClick(riderInfo: RiderInfo)
    }
}