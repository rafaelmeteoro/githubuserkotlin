package com.meteoro.githubkotlin.library

import android.content.Context
import com.meteoro.githubkotlin.core.di.module.ApplicationModule
import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import com.meteoro.githubkotlin.ui.followers.data.repository.GithubFollowersRepository
import com.meteoro.githubkotlin.ui.user.data.repository.GithubUserRepository
import dagger.Component
import rx.Scheduler
import javax.inject.Singleton

/**
 * Created by meteoro on 21/02/2018.
 **/
@Singleton
@Component(modules = [LibraryModule::class, ApplicationModule::class])
interface LibraryComponent {
    fun context(): Context

    @IoScheduler
    fun ioScheduler(): Scheduler

    @UiScheduler
    fun uiScheduler(): Scheduler

    fun githubUserRepository(): GithubUserRepository

    fun githubFollowerRepository(): GithubFollowersRepository
}