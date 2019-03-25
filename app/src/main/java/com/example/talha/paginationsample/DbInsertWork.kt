package com.example.talha.paginationsample

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.talha.paginationsample.api.ApiClient
import com.example.talha.paginationsample.api.ApiInterface
import com.example.talha.paginationsample.db.RepoDao
import com.example.talha.paginationsample.db.RepoEntity
import com.example.talha.paginationsample.db.SampleDatabase
import com.example.talha.paginationsample.model.Repo
import com.example.talha.paginationsample.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Response

class DbInsertWork(context: Context, params:WorkerParameters):Worker(context,params) {

    var repoDao:RepoDao
    private var sampleDatabase: SampleDatabase?=null
    var apiInterface: ApiInterface

    init {
        sampleDatabase = SampleDatabase.getInstance(context)
        repoDao = sampleDatabase!!.repoDao()
        apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
    }
    override fun doWork(): Result {
        var query="Android"
        var apiquiry = query+"in:name,description"
        var lastPage:Int = inputData.getInt("page_no",1)
        Log.d("api_call_page_no",lastPage.toString())
        var call:Call<RepoSearchResponse> = apiInterface.searchReposFromApi(apiquiry,lastPage,50)
        try{
            var response: Response<RepoSearchResponse> = call.execute()
            var result = response.body()!!.items
            var list = ArrayList<RepoEntity>()
            for (i in 0 until result.size){
                var entityObj = result.get(i)
                var id = entityObj.id
                var description = entityObj.description
                var fullName = entityObj.full_name
                var name = entityObj.name
                var url = entityObj.url
                var entity = RepoEntity(id,name,fullName,description,url)
                list.add(entity)

            }
            repoDao.insertRepo(list)

        }catch (e:Exception){

        }
        return Result.success()
    }

}