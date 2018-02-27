package com.meteoro.githubkotlin.ui.user.data.repository

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import rx.Observable

/**
 * Created by meteoro on 21/02/2018.
 **/
interface GithubUserRepository {
    fun getUser(username: String): Observable<GithubUser>
}