package com.meteoro.githubkotlin.application

import android.app.Application
import android.content.Context
import com.meteoro.githubkotlin.core.di.module.ApplicationModule
import com.meteoro.githubkotlin.library.DaggerLibraryComponent
import com.meteoro.githubkotlin.library.LibraryComponent
import com.meteoro.githubkotlin.library.LibraryModule

/**
 * Created by meteoro on 21/02/2018.
 **/
class GithubKotlinApplication : Application() {

    lateinit var component: LibraryComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        component = DaggerLibraryComponent.builder()
                .applicationModule(ApplicationModule(this))
                .libraryModule(LibraryModule(this))
                .build()
    }

    companion object {

        fun get(context: Context): GithubKotlinApplication {
            return context.applicationContext as GithubKotlinApplication
        }
    }
}