package com.meteoro.githubkotlin.core.di.module

import android.support.v7.app.AppCompatActivity
import com.meteoro.githubkotlin.core.di.PerActivity
import dagger.Module
import dagger.Provides

/**
 * Created by meteoro on 21/02/2018.
 **/
@Module
class ActivityModule(var activity: AppCompatActivity) {

    @Provides
    @PerActivity
    fun activity(): AppCompatActivity {
        return activity
    }
}