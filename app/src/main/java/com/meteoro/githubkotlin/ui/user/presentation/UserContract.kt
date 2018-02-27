package com.meteoro.githubkotlin.ui.user.presentation

import com.meteoro.githubkotlin.ui.user.data.models.GithubUser

/**
 * Created by meteoro on 21/02/2018.
 **/
interface UserContract {
    interface View {
        fun showUser()
        fun setUser(user: GithubUser)
        fun showPhoto(photoUrl: String)
        fun showLogin(login: String)
        fun showName(name: String)
        fun showLocation(location: String)
        fun showUserLoading()
        fun showUserError()
    }

    interface Presenter {
        fun initialize(username: String)
    }
}