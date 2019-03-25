package com.example.talha.paginationsample

import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.talha.paginationsample.adapter.RecyclerViewAdapter
import com.example.talha.paginationsample.db.RepoEntity

class MainActivity:AppCompatActivity() {

    //private val lifecycleRegistry = LifecycleRegistry(this)
    lateinit var recyclerView: RecyclerView
    lateinit var fetchDataViewModel:RepoViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchDataViewModel = ViewModelProviders.of(this).get(RepoViewModel::class.java)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        var adapterObj = RecyclerViewAdapter()
        val layoutManage = LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        recyclerView.layoutManager = layoutManage
        recyclerView.adapter = adapterObj


        fetchDataViewModel.getPagedData(this).observe(this,object:android.arch.lifecycle.Observer<PagedList<RepoEntity>>{
            override fun onChanged(list: PagedList<RepoEntity>?) {
                adapterObj.submitList(list)
            }
        })
    }


//    override fun getLifecycle(): Lifecycle {
//        return lifecycleRegistry
//    }

}