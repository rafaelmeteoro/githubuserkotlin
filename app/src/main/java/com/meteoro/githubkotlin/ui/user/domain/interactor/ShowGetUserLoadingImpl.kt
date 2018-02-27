package com.meteoro.githubkotlin.ui.user.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.user.presentation.UserContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class ShowGetUserLoadingImpl @Inject constructor(
        private @UiScheduler val uiScheduler: Scheduler,
        private val view: UserContract.View
) : ShowGetUserLoading {

    override fun call(observable: Observable<String>): Observable<String> {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showLoading)
    }

    private fun showLoading(ignored: String) {
        view.showUserLoading()
    }
}