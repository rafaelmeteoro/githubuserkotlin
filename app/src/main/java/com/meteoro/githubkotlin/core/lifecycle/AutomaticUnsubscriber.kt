package com.meteoro.githubkotlin.core.lifecycle

import android.arch.lifecycle.LifecycleObserver
import rx.Subscription

/**
 * Created by meteoro on 21/02/2018.
 **/
interface AutomaticUnsubscriber : LifecycleObserver {
    fun add(subscription: Subscription)
}