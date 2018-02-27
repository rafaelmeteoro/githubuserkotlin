package com.meteoro.githubkotlin.ui.user.domain.interactor

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import rx.Observable

/**
 * Created by meteoro on 27/02/2018.
 **/
interface ShowUser : Observable.Transformer<GithubUser, GithubUser>