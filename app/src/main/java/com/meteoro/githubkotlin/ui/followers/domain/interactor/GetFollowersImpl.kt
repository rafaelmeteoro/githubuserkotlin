package com.meteoro.githubkotlin.ui.followers.domain.interactor

import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.followers.data.models.Follower
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.data.repository.GithubFollowersRepository
import rx.Observable
import rx.Scheduler
import javax.inject.Inject

/**
 * Created by meteoro on 26/02/2018.
 **/
class GetFollowersImpl @Inject constructor(
        private @IoScheduler val ioScheduler: Scheduler,
        private @UiScheduler val uiScheduler: Scheduler,
        private val repository: GithubFollowersRepository) : GetFollowers {

    override fun call(observable: Observable<String>): Observable<FollowersViewModelHolder> {
        return observable
                .flatMap(this::getFollowers)
    }

    private fun getFollowers(username: String): Observable<FollowersViewModelHolder> {
        return repository.getFollowers(username)
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler)
                .flatMap(this::mapViewModelImage)
                .toList()
                .map(this::mapper)
    }

    private fun mapViewModelImage(followers: List<Follower>): Observable<Follower> {
        return Observable.from(followers)
                .map(this::mapper)
    }

    private fun mapper(follower: Follower): Follower {
        follower.isLeft = ((follower.id % 2) == 0)
        return follower
    }

    private fun mapper(followers: List<Follower>): FollowersViewModelHolder {
        return FollowersViewModelHolder(followers)
    }
}