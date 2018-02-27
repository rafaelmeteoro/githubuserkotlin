package com.meteoro.githubkotlin.ui.user.data.repository

import com.meteoro.githubkotlin.core.client.ApiClientUtil
import com.meteoro.githubkotlin.core.client.Builder
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import rx.Observable

/**
 * Created by meteoro on 21/02/2018.
 **/
class GithubUserRepositoryImpl(host: String) : GithubUserRepository {

    var api: GithubUserApi

    init {
        val builder = Builder(host, true)
        val client = ApiClientUtil(builder)
        api = client.create(GithubUserApi::class.java)
    }

    override fun getUser(username: String): Observable<GithubUser> {
        return api.getUser(username)
    }
}