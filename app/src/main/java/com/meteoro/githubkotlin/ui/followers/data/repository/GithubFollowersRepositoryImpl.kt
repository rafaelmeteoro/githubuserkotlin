package com.meteoro.githubkotlin.ui.followers.data.repository

import com.meteoro.githubkotlin.core.client.ApiClientUtil
import com.meteoro.githubkotlin.core.client.Builder
import com.meteoro.githubkotlin.ui.followers.data.models.Follower
import rx.Observable

/**
 * Created by meteoro on 26/02/2018.
 **/
class GithubFollowersRepositoryImpl(host: String) : GithubFollowersRepository {

    var api: GithubFollowerApi

    init {
        val builder = Builder(host, true)
        val client = ApiClientUtil(builder)
        api = client.create(GithubFollowerApi::class.java)
    }

    override fun getFollowers(username: String): Observable<List<Follower>> {
        return api.getFollowers(username)
    }
}