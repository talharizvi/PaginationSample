package com.example.talha.paginationsample.api

import android.util.Log

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.SocketTimeoutException


class ApiClient {


    companion object {
        var retrofit: Retrofit? = null

        val BASE_URL ="https://api.github.com/"



        fun getClient(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            var client = OkHttpClient.Builder()
                    .connectTimeout(300,TimeUnit.SECONDS)
                    .readTimeout(360,TimeUnit.SECONDS)
                    //.addInterceptor(UrlEncodeInterceptor())
                    .addInterceptor(interceptor)
//                .addInterceptor(InterceptorUrl())

                    .build()

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())

                    .client(client)
                    .build()
            return retrofit!!
        }


    }
}