package com.example.talha.paginationsample

import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Context
import android.support.v4.app.FragmentActivity
import com.example.talha.paginationsample.db.RepoEntity

class RepoBoundaryCallback(var context:Context): PagedList.BoundaryCallback<RepoEntity>() {

    private var isRequestInProgress = false
    private var lastRequestedPage = 1
    lateinit var fetchDataViewModel: RepoViewModel

    init {
        fetchDataViewModel = ViewModelProviders.of(context as FragmentActivity).get(RepoViewModel::class.java)
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoEntity) {
        super.onItemAtEndLoaded(itemAtEnd)
        requestAndSaveData("","")
    }

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        requestAndSaveData("","")
    }

    fun requestAndSaveData(tokenId: String, categoryName: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        lastRequestedPage++
        fetchDataViewModel.getNextNews(tokenId, categoryName,lastRequestedPage)
        isRequestInProgress = false
    }

}