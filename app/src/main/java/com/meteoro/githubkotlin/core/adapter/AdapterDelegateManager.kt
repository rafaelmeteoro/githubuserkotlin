package com.meteoro.githubkotlin.core.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.ViewGroup

/**
 * Created by meteoro on 26/02/2018.
 **/
class AdapterDelegateManager<T> {

    private var viewTypeCount = 0
    private var delegates: SparseArray<AdapterDelegate<T>> = SparseArray()

    fun addDelegate(delegate: AdapterDelegate<T>) {
        delegates.append(viewTypeCount, delegate)
        viewTypeCount++
    }

    fun removeAllDelegates() {
        delegates.clear()
    }

    fun getItemViewType(data: T, position: Int): Int {
        return getDelegateViewType(data, position)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getDelegateWithType(viewType).onCreateViewHolder(parent)
    }

    fun onBindViewHolder(data: T, position: Int, holder: RecyclerView.ViewHolder) {
        getDelegateForDataType(data, position).onBindViewHolder(data, position, holder)
    }

    fun getAdapterDelegate(position: Int): AdapterDelegate<T> {
        return delegates.get(delegates.keyAt(position))
    }

    fun size(): Int {
        return delegates.size()
    }

    private fun getDelegateViewType(data: T, position: Int): Int {
        for (i in 0 until delegates.size()) {
            val key = delegates.keyAt(i)
            val delegate = delegates.get(key)
            if (delegate.isViewForData(data, position)) {
                return key
            }
        }

        throw IllegalArgumentException("Missing adapter for data at position ${position}")
    }

    private fun getDelegateForDataType(data: T, position: Int): AdapterDelegate<T> {
        for (i in 0 until delegates.size()) {
            val delegate = delegates.get(delegates.keyAt(i))
            if (delegate.isViewForData(data, position)) {
                return delegate
            }
        }

        throw IllegalArgumentException("Missing adapter for data type ${data.toString()}")
    }

    private fun getDelegateWithType(viewType: Int): AdapterDelegate<T> {
        return delegates.get(viewType)
    }
}