package com.meteoro.githubkotlin.ui.followers.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class ShowGetFollowersLoadingImpl @Inject constructor(
        private @UiScheduler val uiScheduler: Scheduler,
        private val view: FollowersContract.View
) : ShowGetFollowersLoading {

    override fun call(observable: Observable<String>): Observable<String> {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showLoading)
    }

    private fun showLoading(ignored: String) {
        view.showFollowersLoading()
    }
}