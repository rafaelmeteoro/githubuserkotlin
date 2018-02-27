package com.meteoro.githubkotlin.ui.followers.presentation

import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.domain.interactor.GetFollowers
import com.meteoro.githubkotlin.ui.followers.presentation.coordinator.FollowersCoordinator
import rx.Observable
import javax.inject.Inject

/**
 * Created by meteoro on 26/02/2018.
 **/
class FollowersPresenter @Inject constructor(
        private val followersCoordinator: FollowersCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber) : FollowersContract.Presenter {

    override fun initialize(username: String) {
        val subscription = Observable.just(username)
                .compose(followersCoordinator)
                .subscribe()

        automaticUnsubscriber.add(subscription)
    }
}