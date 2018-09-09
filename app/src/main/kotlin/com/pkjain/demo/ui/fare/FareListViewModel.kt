package com.pkjain.demo.ui.fare

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.pkjain.R
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.Fare
import com.pkjain.demo.model.PostDao
import com.pkjain.demo.network.PostApi
import javax.inject.Inject

class FareListViewModel(private val itemList: List<Fare>, private val postDao: PostDao? = null, private val presenter: Presenter) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val fareListAdapter: FareListAdapter = FareListAdapter(presenter)

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

    private fun onRetrievePostListSuccess(itemList: List<Fare>) {
        fareListAdapter.updatePostList(itemList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    interface Presenter {

        fun onClick(fare: Fare)
    }
}