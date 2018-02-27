package com.meteoro.githubkotlin.ui.user.di

import android.arch.lifecycle.LifecycleOwner
import com.meteoro.githubkotlin.core.di.PerActivity
import com.meteoro.githubkotlin.core.lifecycle.AutomaticUnsubscriber
import com.meteoro.githubkotlin.core.lifecycle.LifecycleUnsubscriber
import com.meteoro.githubkotlin.ui.user.domain.interactor.GetUser
import com.meteoro.githubkotlin.ui.user.domain.interactor.GetUserImpl
import com.meteoro.githubkotlin.ui.user.presentation.UserActivity
import com.meteoro.githubkotlin.ui.user.presentation.UserContract
import com.meteoro.githubkotlin.ui.user.presentation.UserPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by meteoro on 21/02/2018.
 **/
@Module
class UserModule(var activity: UserActivity) {

    @Provides
    @PerActivity
    fun view(): UserContract.View {
        return activity
    }

    @Provides
    @PerActivity
    fun presenter(presenter: UserPresenter): UserContract.Presenter {
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
    fun getUser(impl: GetUserImpl): GetUser {
        return impl
    }
}