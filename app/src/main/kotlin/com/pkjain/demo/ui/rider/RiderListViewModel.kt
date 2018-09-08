package com.pkjain.demo.ui.rider

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.pkjain.R
import com.pkjain.demo.base.BaseViewModel
import com.pkjain.demo.model.PostDao
import com.pkjain.demo.model.RiderInfo
import com.pkjain.demo.network.PostApi
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class RiderListViewModel(private val itemList: List<RiderInfo>, private val postDao: PostDao? = null) : BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi
    val riderListAdapter: RiderListAdapter = RiderListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {

        onRetrievePostListSuccess(itemList);
//        subscription = Observable.fromCallable { postDao.all }
//                .concatMap {
//                    dbPostList ->
//                        if(dbPostList.isEmpty())
//                            postApi.getPosts().concatMap {
//                                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
//                                 Observable.just(apiPostList)
//                                       }
//                        else
//                            Observable.just(dbPostList)
//                }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { onRetrievePostListStart() }
//                .doOnTerminate { onRetrievePostListFinish() }
//                .subscribe(
//                        { result -> onRetrievePostListSuccess(result) },
//                        { onRetrievePostListError() }
//                )
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
}