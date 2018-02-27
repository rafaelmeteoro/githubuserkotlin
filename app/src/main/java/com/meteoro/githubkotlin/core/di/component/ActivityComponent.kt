package com.meteoro.githubkotlin.core.di.component

import android.support.v7.app.AppCompatActivity
import com.meteoro.githubkotlin.core.di.PerActivity
import com.meteoro.githubkotlin.core.di.module.ActivityModule
import dagger.Component

/**
 * Created by meteoro on 21/02/2018.
 **/
@PerActivity
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class]
)
interface ActivityComponent {
    fun activity(): AppCompatActivity
}
