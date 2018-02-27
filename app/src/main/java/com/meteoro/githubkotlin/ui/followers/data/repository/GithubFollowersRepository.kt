package com.meteoro.githubkotlin.ui.followers.data.repository

import com.meteoro.githubkotlin.ui.followers.data.models.Follower
import rx.Observable

/**
 * Created by meteoro on 26/02/2018.
 **/
interface GithubFollowersRepository {
    fun getFollowers(username: String): Observable<List<Follower>>
}