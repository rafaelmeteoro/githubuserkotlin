package com.meteoro.githubkotlin.ui.user.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by meteoro on 21/02/2018.
 **/
data class GithubUser(
        val login: String,
        val id: Int,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("gravatar_id") val gravatarId: String,
        val url: String,
        @SerializedName("html_url") val html_ulr: String,
        @SerializedName("followers_url") val followersUrl: String,
        @SerializedName("following_url") val followingUrl: String,
        @SerializedName("gists_url") val gistsUrl: String,
        @SerializedName("starred_url") val starredUrl: String,
        @SerializedName("subscriptions_url") val subscriptionsUrl: String,
        @SerializedName("organizations_url") val organizationsUrl: String,
        @SerializedName("repos_url") val reposUrl: String,
        @SerializedName("events_url") val eventsUrl: String,
        @SerializedName("received_events_url") val receivedEventsUrl: String,
        val type: String,
        @SerializedName("site_admin") val siteAdmin: Boolean,
        val name: String,
        val company: String,
        val blog: String,
        val location: String,
        val email: String,
        val hireable: String,
        val bio: String,
        @SerializedName("public_repos") val publicRepos: Int,
        @SerializedName("public_gists") val publicGists: Int,
        val followers: Int,
        val following: Int,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String
)