package com.meteoro.githubkotlin.core.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by meteoro on 26/02/2018.
 **/
interface AdapterDelegate<T> {
    fun isViewForData(data: T, position: Int): Boolean
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(data: T, position: Int, holder: RecyclerView.ViewHolder)
}