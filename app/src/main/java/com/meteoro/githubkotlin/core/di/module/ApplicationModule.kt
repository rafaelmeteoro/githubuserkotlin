package com.meteoro.githubkotlin.core.di.module

import android.content.Context
import com.meteoro.githubkotlin.core.di.qualifers.IoScheduler
import com.meteoro.githubkotlin.core.di.qualifers.UiScheduler
import dagger.Module
import dagger.Provides
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by meteoro on 21/02/2018.
 **/
@Module
class ApplicationModule(var context: Context) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context {
        return context.applicationContext
    }

    @Provides
    @Singleton
    @UiScheduler
    fun providesMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Singleton
    @IoScheduler
    fun providesJobScheduler(): Scheduler {
        return Schedulers.io()
    }
}