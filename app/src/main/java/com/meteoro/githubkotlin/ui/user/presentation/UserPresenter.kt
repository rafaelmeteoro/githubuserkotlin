package com.meteoro.githubkotlin.ui.user.presentation

import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.domain.interactor.GetUser
import com.meteoro.githubkotlin.ui.user.presentation.coordinator.GithubUserCoordinator
import rx.Observable
import javax.inject.Inject

/**
 * Created by meteoro on 21/02/2018.
 **/
class UserPresenter @Inject constructor(
        private val githubUserCoordinator: GithubUserCoordinator,
        private val automaticUnsubscriber: AutomaticUnsubscriber
) : UserContract.Presenter {

    override fun initialize(username: String) {
        val subscription =
                Observable.just(username)
                        .compose(githubUserCoordinator)
                        .subscribe()

        automaticUnsubscriber.add(subscription)
    }
}