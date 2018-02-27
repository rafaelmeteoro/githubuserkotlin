package com.meteoro.githubkotlin.ui.user.presentation.coordinator

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.domain.interactor.GetUser
import com.meteoro.githubkotlin.ui.user.domain.interactor.ShowGetUserLoading
import com.meteoro.githubkotlin.ui.user.domain.interactor.ShowUser
import rx.Observable
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class GithubUserCoordinator @Inject constructor(
        private val showGetUserLoading: ShowGetUserLoading,
        private val getUser: GetUser,
        private val showUser: ShowUser
) : Observable.Transformer<String, GithubUser> {

    override fun call(observable: Observable<String>): Observable<GithubUser> {
        return observable
                .compose(showGetUserLoading)
                .compose(getUser)
                .compose(showUser)
    }
}