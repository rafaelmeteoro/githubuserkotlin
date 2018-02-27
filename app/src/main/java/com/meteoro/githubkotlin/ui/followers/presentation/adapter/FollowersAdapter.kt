package com.meteoro.githubkotlin.ui.followers.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.meteoro.githubkotlin.core.adapter.AdapterDelegateManager
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.presentation.listener.OnLeftFollowerClickListener
import com.meteoro.githubkotlin.ui.followers.presentation.listener.OnRightFollowerClickListener
import javax.inject.Inject

/**
 * Created by meteoro on 26/02/2018.
 **/
class FollowersAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data: FollowersViewModelHolder? = null

    var delegateManager: AdapterDelegateManager<FollowersViewModelHolder> = AdapterDelegateManager()
    var leftFollowerAdapterDelegate: LeftFollowerAdapterDelegate = LeftFollowerAdapterDelegate()
    var rightFollowerAdapterDelegate: RightFollowerAdapterDelegate = RightFollowerAdapterDelegate()

    init {
        delegateManager.addDelegate(leftFollowerAdapterDelegate)
        delegateManager.addDelegate(rightFollowerAdapterDelegate)
    }

    fun setData(data: FollowersViewModelHolder) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setLeftFollowerClickListener(listener: OnLeftFollowerClickListener) {
        leftFollowerAdapterDelegate.setOnFollowerClickListener(listener)
    }

    fun setRightFollowerClickListener(listener: OnRightFollowerClickListener) {
        rightFollowerAdapterDelegate.setOnFollowerClickListener(listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        parent?.let {
            return delegateManager.onCreateViewHolder(it, viewType)
        }

        return null
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (data != null && holder != null) {
            delegateManager.onBindViewHolder(data!!, position, holder)
        }
    }

    override fun getItemCount(): Int {
        if (data != null) {
            return data!!.viewModels.size
        } else {
            return 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return delegateManager.getItemViewType(data!!, position)
    }
}