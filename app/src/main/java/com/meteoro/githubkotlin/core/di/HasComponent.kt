package com.meteoro.githubkotlin.core.di

/**
 * Created by meteoro on 21/02/2018.
 **/
interface HasComponent<T> {
    fun getComponent(): T
}