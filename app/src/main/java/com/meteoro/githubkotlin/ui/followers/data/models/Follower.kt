package com.meteoro.githubkotlin.ui.followers.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by meteoro on 26/02/2018.
 **/
data class Follower(
        val login: String,
        val id: Int,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("gravatar_url") val gravatarId: String,
        val url: String,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("followers_url") val followersUrl: String,
        @SerializedName("following_url") val followingUrl: String,
        @SerializedName("gitsts_url") val gistsUrl: String,
        @SerializedName("starred_url") val starredUrl: String,
        @SerializedName("subscriptions_url") val subscriptionsUrl: String,
        @SerializedName("organizations_url") val organizationsUrl: String,
        @SerializedName("repos_url") val reposUrl: String,
        @SerializedName("events_url") val eventsUrl: String,
        @SerializedName("received_events_url") val receivedEventsUrl: String,
        val type: String,
        @SerializedName("site_admin") val siteAdmin: Boolean,
        var isLeft: Boolean
)