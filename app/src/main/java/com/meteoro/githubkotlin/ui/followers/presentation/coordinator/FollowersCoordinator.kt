package com.meteoro.githubkotlin.ui.followers.presentation.coordinator

import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.domain.interactor.GetFollowers
import com.meteoro.githubkotlin.ui.followers.domain.interactor.ShowFollowers
import com.meteoro.githubkotlin.ui.followers.domain.interactor.ShowGetFollowersLoading
import rx.Observable
import javax.inject.Inject

/**
 * Created by meteoro on 27/02/2018.
 **/
class FollowersCoordinator @Inject constructor(
        private val showGetFollowersLoading: ShowGetFollowersLoading,
        private val getFollowers: GetFollowers,
        private val showFollowers: ShowFollowers
) : Observable.Transformer<String, FollowersViewModelHolder> {

    override fun call(observable: Observable<String>): Observable<FollowersViewModelHolder> {
        return observable
                .compose(showGetFollowersLoading)
                .compose(getFollowers)
                .compose(showFollowers)
    }
}