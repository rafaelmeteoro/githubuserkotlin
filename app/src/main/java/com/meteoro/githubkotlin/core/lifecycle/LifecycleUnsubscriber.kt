package com.meteoro.githubkotlin.core.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by meteoro on 21/02/2018.
 **/
class LifecycleUnsubscriber @Inject constructor(owner: LifecycleOwner) : AutomaticUnsubscriber {

    var subscription: CompositeSubscription

    init {
        subscription = CompositeSubscription()
        owner.lifecycle.addObserver(this)
    }

    override fun add(subscription: Subscription) {
        this.subscription.add(subscription)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun dispose() {
        subscription.unsubscribe()
    }
}