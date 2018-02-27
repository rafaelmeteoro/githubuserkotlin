package com.meteoro.githubkotlin.ui.followers.di

import android.arch.lifecycle.LifecycleOwner
import com.meteoro.githubkotlin.core.di.PerActivity
import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.core.lifecycle.LifecycleUnsubscriber
import com.meteoro.githubkotlin.ui.followers.domain.interactor.*
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersActivity
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersContract
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by meteoro on 26/02/2018.
 **/
@Module
class FollowersModule(var activity: FollowersActivity) {

    @Provides
    @PerActivity
    fun view(): FollowersContract.View {
        return activity
    }

    @Provides
    @PerActivity
    fun presenter(presenter: FollowersPresenter): FollowersContract.Presenter {
        return presenter
    }

    @Provides
    @PerActivity
    fun lifecycleOwner(): LifecycleOwner {
        return activity
    }

    @Provides
    @PerActivity
    fun automaticUnsubscriber(impl: LifecycleUnsubscriber): AutomaticUnsubscriber {
        return impl
    }

    @Provides
    @PerActivity
    fun showGetFollowersLoading(impl: ShowGetFollowersLoadingImpl): ShowGetFollowersLoading {
        return impl
    }

    @Provides
    @PerActivity
    fun getFollowers(impl: GetFollowersImpl): GetFollowers {
        return impl
    }

    @Provides
    @PerActivity
    fun showFollowers(impl: ShowFollowersImpl): ShowFollowers {
        return impl
    }
}