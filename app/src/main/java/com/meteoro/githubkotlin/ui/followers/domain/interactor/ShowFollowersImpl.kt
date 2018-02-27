package com.meteoro.githubkotlin.ui.followers.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.data.repository.GithubFollowersRepository
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersContract
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class ShowFollowersImpl @Inject constructor(
        private @UiScheduler val uiScheduler: Scheduler,
        private val view: FollowersContract.View
) : ShowFollowers {

    override fun call(observable: Observable<FollowersViewModelHolder>): Observable<FollowersViewModelHolder> {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showFollowers)
                .doOnError(this::showErrorFollowers)
                .onErrorResumeNext(Observable.empty())
    }

    private fun showFollowers(holder: FollowersViewModelHolder) {
        view.showFollowers(holder)
    }

    private fun showErrorFollowers(error: Throwable) {
        error.printStackTrace()
        view.showFollowersError()
    }
}