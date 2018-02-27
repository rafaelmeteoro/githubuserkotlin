package com.meteoro.githubkotlin.ui.user.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.presentation.UserContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class ShowUserImpl @Inject constructor(
        private @UiScheduler val uiScheduler: Scheduler,
        private val view: UserContract.View
) : ShowUser {

    override fun call(observable: Observable<GithubUser>): Observable<GithubUser> {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showUser)
                .doOnError(this::showErrorUser)
                .onErrorResumeNext(Observable.empty())
    }

    private fun showUser(githubUser: GithubUser) {
        view.showUser()
        view.setUser(githubUser)
        view.showPhoto(githubUser.avatarUrl)
        view.showLogin(githubUser.login)
        view.showName(githubUser.name)
        view.showLocation(githubUser.location)
    }


    private fun showErrorUser(error: Throwable) {
        error.printStackTrace()
        view.showUserError()
    }
}