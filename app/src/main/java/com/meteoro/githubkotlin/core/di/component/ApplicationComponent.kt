package com.meteoro.githubkotlin.core.di.component

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.meteoro.githubkotlin.core.di.module.ApplicationModule
import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import dagger.Component
import rx.Scheduler
import javax.inject.Singleton

/**
 * Created by meteoro on 21/02/2018.
 **/
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(activity: AppCompatActivity)

    fun context(): Context

    @UiScheduler
    fun mainThreadSchduler(): Scheduler

    @IoScheduler
    fun jobScheduler(): Scheduler
}