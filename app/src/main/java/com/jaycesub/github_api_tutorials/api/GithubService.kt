package com.jaycesub.github_api_tutorials.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GithubService {

    private val githubBaseUrl = "https://api.github.com/"
    var service: GithubApi

    init {
        service = getRetrofitClient().create(GithubApi::class.java)
    }

    private fun getRetrofitClient() = Retrofit.Builder()
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(githubBaseUrl)
        .build()

    private fun getOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getNetworkInterceptor())
        .build()

    private fun getNetworkInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}