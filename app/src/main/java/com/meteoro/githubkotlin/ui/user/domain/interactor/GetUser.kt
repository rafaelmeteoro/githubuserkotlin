package com.meteoro.githubkotlin.ui.user.domain.interactor

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import rx.Observable

/**
 * Created by meteoro on 21/02/2018.
 **/
interface GetUser {
    fun getUser(username: String): Observable<GithubUser>
}