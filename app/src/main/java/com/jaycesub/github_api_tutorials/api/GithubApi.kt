package com.jaycesub.github_api_tutorials.api

import com.jaycesub.github_api_tutorials.model.User
import com.jaycesub.github_api_tutorials.model.Users
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    @GET("/search/users")
    fun getUserList(@Query("q") q: String): Observable<Users>

    @GET("/users/{username}")
    fun getUserInfo(@Path("username") userName: String): Observable<User>
}