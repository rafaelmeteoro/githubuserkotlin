package com.meteoro.githubkotlin.ui.user.presentation

import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.domain.interactor.GetUser
import javax.inject.Inject

/**
 * Created by meteoro on 21/02/2018.
 **/
class UserPresenter @Inject constructor(
        private val getUser: GetUser,
        private val view: UserContract.View,
        private val automaticUnsubscriber: AutomaticUnsubscriber) : UserContract.Presenter {

    override fun initialize(username: String) {
        view.showUserLoading()

        val subscription = getUser.getUser(username).subscribe(
                this::setGithubUser,
                this::setErrorUser
        )

        automaticUnsubscriber.add(subscription)
    }

    private fun setGithubUser(githubUser: GithubUser) {
        view.showUser()
        view.setUser(githubUser)
        view.showPhoto(githubUser.avatarUrl)
        view.showLogin(githubUser.login)
        view.showName(githubUser.name)
        view.showLocation(githubUser.location)
    }

    private fun setErrorUser(error: Throwable) {
        error.printStackTrace()
        view.showUserError()
    }
}