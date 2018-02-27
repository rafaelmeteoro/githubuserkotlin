package com.meteoro.githubkotlin.ui.followers.domain.interactor

import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import rx.Observable

/**
 * Created by meteoro on 27/02/2018.
 **/
interface ShowFollowers : Observable.Transformer<FollowersViewModelHolder, FollowersViewModelHolder>