package com.meteoro.githubkotlin.ui.user.domain.interactor

import rx.Observable

/**
 * Created by meteoro on 27/02/2018.
 **/
interface ShowGetUserLoading : Observable.Transformer<String, String>