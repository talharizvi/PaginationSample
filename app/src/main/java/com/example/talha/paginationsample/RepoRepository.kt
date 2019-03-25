package com.example.talha.paginationsample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import com.example.talha.paginationsample.db.RepoDao
import com.example.talha.paginationsample.db.RepoEntity
import com.example.talha.paginationsample.db.SampleDatabase

class RepoRepository(application: Application) {

    var repoDao:RepoDao
    private var repoDatabase:SampleDatabase?=null
    lateinit var repoList: LiveData<List<RepoEntity>>

    init {
        repoDatabase = SampleDatabase.getInstance(application)
        repoDao = repoDatabase!!.repoDao()
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 10
    }

    fun getPageListData(mContext: Context):LiveData<PagedList<RepoEntity>>{

        var dataSourceFactory = repoDao.reposByName()
        var boundaryCallBack = RepoBoundaryCallback(mContext)
        val data =  LivePagedListBuilder(dataSourceFactory,20)
                .setBoundaryCallback(boundaryCallBack)
                .build()

        return data
    }

}