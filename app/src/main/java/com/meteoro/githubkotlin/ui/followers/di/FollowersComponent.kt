package com.meteoro.githubkotlin.ui.followers.di

import com.meteoro.githubkotlin.core.di.PerActivity
import com.meteoro.githubkotlin.library.LibraryComponent
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersActivity
import dagger.Component

/**
 * Created by meteoro on 26/02/2018.
 **/
@PerActivity
@Component(
        dependencies = [LibraryComponent::class],
        modules = [FollowersModule::class]
)
interface FollowersComponent {
    fun inject(activity: FollowersActivity)
}