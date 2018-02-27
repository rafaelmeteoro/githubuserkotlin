package com.meteoro.githubkotlin.ui.user.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.data.repository.GithubUserRepository
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 21/02/2018.
 **/
class GetUserImpl @Inject constructor(
        private @IoScheduler val ioScheduler: Scheduler,
        private @UiScheduler val uiScheduler: Scheduler,
        private val repository: GithubUserRepository
) : GetUser {

    override fun call(observable: Observable<String>): Observable<GithubUser> {
        return observable
                .flatMap(this::getUser)
    }

    private fun getUser(username: String): Observable<GithubUser> {
        return repository.getUser(username)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler)
    }
}