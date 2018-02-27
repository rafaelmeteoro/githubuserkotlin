package com.meteoro.githubkotlin.ui.followers.presentation.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.meteoro.githubkotlin.R
import com.meteoro.githubkotlin.core.adapter.AdapterDelegate
import com.meteoro.githubkotlin.ui.followers.data.models.Follower
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.presentation.data.LeftFollowerClickData
import com.meteoro.githubkotlin.ui.followers.presentation.listener.OnLeftFollowerClickListener
import com.squareup.picasso.Picasso

/**
 * Created by meteoro on 26/02/2018.
 **/
class LeftFollowerAdapterDelegate : AdapterDelegate<FollowersViewModelHolder> {

    private var listener: OnLeftFollowerClickListener? = null

    override fun isViewForData(data: FollowersViewModelHolder, position: Int): Boolean {
        return getItem(data, position).isLeft
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.follower_item_left, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(data: FollowersViewModelHolder, position: Int, holder: RecyclerView.ViewHolder) {
        val follower = getItem(data, position)
        val viewHolder = holder as ViewHolder

        viewHolder.textLogin.text = follower.login
        viewHolder.textUrl.text = follower.url
        viewHolder.itemView.setOnClickListener {
            callOnFollowerClickIfNotNull(follower)
        }

        Picasso.with(viewHolder.itemView.context)
                .load(follower.avatarUrl)
                .into(viewHolder.imageAvatar)
    }

    private fun getItem(holder: FollowersViewModelHolder, position: Int): Follower {
        return holder.viewModels.get(position)
    }

    private fun callOnFollowerClickIfNotNull(follower: Follower) {
        listener?.onClick(LeftFollowerClickData(follower.htmlUrl))
    }

    fun setOnFollowerClickListener(listener: OnLeftFollowerClickListener) {
        this.listener = listener
    }

    protected class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageAvatar = itemView.findViewById(R.id.image_avatar) as ImageView
        val textLogin = itemView.findViewById(R.id.text_login) as TextView
        val textUrl = itemView.findViewById(R.id.text_url) as TextView
    }
}