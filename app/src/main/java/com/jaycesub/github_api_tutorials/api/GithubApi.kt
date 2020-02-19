package com.jaycesub.github_api_tutorials.api

import com.jaycesub.github_api_tutorials.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    fun getUser(@Query("q") q: String): Observable<User>
}