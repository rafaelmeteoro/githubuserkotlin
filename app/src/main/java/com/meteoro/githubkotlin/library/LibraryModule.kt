package com.meteoro.githubkotlin.library

import android.content.Context
import com.meteoro.githubkotlin.R
import com.meteoro.githubkotlin.ui.followers.data.repository.GithubFollowersRepository
import com.meteoro.githubkotlin.ui.followers.data.repository.GithubFollowersRepositoryImpl
import com.meteoro.githubkotlin.ui.user.data.repository.GithubUserRepository
import com.meteoro.githubkotlin.ui.user.data.repository.GithubUserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by meteoro on 21/02/2018.
 **/
@Module
class LibraryModule(private val context: Context) {

    @Provides
    @Singleton
    fun providesGithubUserRepository(): GithubUserRepository {
        val host = context.getString(R.string.api_host)
        return GithubUserRepositoryImpl(host)
    }

    @Provides
    @Singleton
    fun providesGithubFollowerRepository(): GithubFollowersRepository {
        val host = context.getString(R.string.api_host)
        return GithubFollowersRepositoryImpl(host)
    }
}
