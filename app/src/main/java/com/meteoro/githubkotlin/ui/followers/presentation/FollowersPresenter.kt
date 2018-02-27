package com.meteoro.githubkotlin.ui.followers.presentation

import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.domain.interactor.GetFollowers
import javax.inject.Inject

/**
 * Created by meteoro on 26/02/2018.
 **/
class FollowersPresenter @Inject constructor(
        private val getFollowers: GetFollowers,
        private val view: FollowersContract.View,
        private val automaticUnsubscriber: AutomaticUnsubscriber) : FollowersContract.Presenter {

    override fun initialize(username: String) {
        view.showFollowersLoading()

        val subscription = getFollowers.getFollowers(username).subscribe(
                this::setFollowers,
                this::setErrorFollower
        )

        automaticUnsubscriber.add(subscription)
    }

    private fun setFollowers(holder: FollowersViewModelHolder) {
        view.showFollowers(holder)
    }

    private fun setErrorFollower(error: Throwable) {
        error.printStackTrace()
        view.showFollowersError()
    }
}