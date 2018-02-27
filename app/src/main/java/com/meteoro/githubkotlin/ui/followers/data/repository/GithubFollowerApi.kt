package com.meteoro.githubkotlin.ui.followers.data.repository

import com.meteoro.githubkotlin.ui.followers.data.models.Follower
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by meteoro on 26/02/2018.
 **/
interface GithubFollowerApi {
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Observable<List<Follower>>
}