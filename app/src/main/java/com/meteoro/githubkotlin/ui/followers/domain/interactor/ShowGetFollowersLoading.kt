package com.meteoro.githubkotlin.ui.followers.domain.interactor

import rx.Observable

/**
 * Created by meteoro on 27/02/2018.
 **/
interface ShowGetFollowersLoading : Observable.Transformer<String, String>