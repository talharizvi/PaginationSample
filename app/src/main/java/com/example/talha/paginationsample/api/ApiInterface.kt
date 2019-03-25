package com.example.talha.paginationsample.api

import com.example.talha.paginationsample.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("search/repositories?sort=stars")
    fun searchReposFromApi(
            @Query("q") query: String,
            @Query("page") page: Int,
            @Query("per_page") itemsPerPage: Int
    ): Call<RepoSearchResponse>


}