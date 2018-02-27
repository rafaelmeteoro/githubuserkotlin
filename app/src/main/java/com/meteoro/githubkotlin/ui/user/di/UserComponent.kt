package com.meteoro.githubkotlin.ui.user.di

import com.meteoro.githubkotlin.core.di.PerActivity
import com.meteoro.githubkotlin.library.LibraryComponent
import com.meteoro.githubkotlin.ui.user.presentation.UserActivity
import dagger.Component

/**
 * Created by meteoro on 21/02/2018.
 **/
@PerActivity
@Component(
        dependencies = [LibraryComponent::class],
        modules = [UserModule::class]
)
interface UserComponent {
    fun inject(activity: UserActivity)
}