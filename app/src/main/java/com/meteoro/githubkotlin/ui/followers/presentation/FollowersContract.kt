package com.meteoro.githubkotlin.ui.followers.presentation

import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder

/**
 * Created by meteoro on 26/02/2018.
 **/
interface FollowersContract {
    interface View {
        fun showFollowers(holder: FollowersViewModelHolder)
        fun showFollowersLoading()
        fun showFollowersError()
    }

    interface Presenter {
        fun initialize(username: String)
    }
}