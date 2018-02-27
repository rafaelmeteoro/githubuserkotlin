package com.meteoro.githubkotlin.ui.user.data.repository

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by meteoro on 21/02/2018.
 **/
interface GithubUserApi {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<GithubUser>
}