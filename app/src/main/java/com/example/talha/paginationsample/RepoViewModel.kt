package com.example.talha.paginationsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.talha.paginationsample.api.ApiClient
import com.example.talha.paginationsample.api.ApiInterface
import com.example.talha.paginationsample.db.RepoDao
import com.example.talha.paginationsample.db.RepoEntity
import com.example.talha.paginationsample.db.SampleDatabase
import com.example.talha.paginationsample.model.Repo

class RepoViewModel(application: Application):AndroidViewModel(application) {


    var apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
    var workManager : WorkManager = WorkManager.getInstance()
    //var repoDao:RepoDao
    private var sampleDatabase: SampleDatabase?=null
    lateinit var  repository:RepoRepository
    lateinit var mContext:Context
    var mApplication:Application

    init {

        repository = RepoRepository(application)
  //      repoDao = sampleDatabase!!.repoDao()
        mApplication = application
    }

//    constructor(context: Context):this(application = mApplication){
//
//    }

    fun getNextNews(token:String,category:String,lastPage:Int) {


        // fetchNewsFromApi(token,category)
        startArticleWorkManager(lastPage)
    }

    fun startArticleWorkManager(page:Int){
        var dbArticleInsertWork = OneTimeWorkRequest.Builder(DbInsertWork::class.java)
        var data = Data.Builder()
        data.putInt("page_no",page)
      //  data.putString("tag_value",tag)
       // data.putString("cursor_string_value",cursor)
        dbArticleInsertWork.setInputData(data.build())
        workManager.enqueue(dbArticleInsertWork.build())
    }

//    fun getPageListData(mContext:Context):LiveData<PagedList<RepoEntity>>{//:LiveData<PagedList<DetailNewsData>>{
//
//        var dataSourceFactory = repoDao.reposByName()
//        var boundaryCallBack = RepoBoundaryCallback(mContext)
//        val data =  LivePagedListBuilder(dataSourceFactory,20)
//                .setBoundaryCallback(boundaryCallBack)
//                .build()
//
//        return data
//    }

    fun getPagedData(context:Context):LiveData<PagedList<RepoEntity>>{
        var result = repository.getPageListData(context)
        return result
    }

}