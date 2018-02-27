package com.meteoro.githubkotlin.ui.followers.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.kennyc.view.MultiStateView
import com.meteoro.githubkotlin.R
import com.meteoro.githubkotlin.application.GithubKotlinApplication
import com.meteoro.githubkotlin.core.di.HasComponent
import com.meteoro.githubkotlin.library.LibraryComponent
import com.meteoro.githubkotlin.ui.followers.data.models.FollowersViewModelHolder
import com.meteoro.githubkotlin.ui.followers.di.DaggerFollowersComponent
import com.meteoro.githubkotlin.ui.followers.di.FollowersComponent
import com.meteoro.githubkotlin.ui.followers.di.FollowersModule
import com.meteoro.githubkotlin.ui.followers.presentation.adapter.FollowersAdapter
import com.meteoro.githubkotlin.ui.followers.presentation.data.LeftFollowerClickData
import com.meteoro.githubkotlin.ui.followers.presentation.data.RightFollowerClickdata
import com.meteoro.githubkotlin.ui.followers.presentation.listener.OnLeftFollowerClickListener
import com.meteoro.githubkotlin.ui.followers.presentation.listener.OnRightFollowerClickListener
import javax.inject.Inject

/**
 * Created by meteoro on 26/02/2018.
 **/
class FollowersActivity : AppCompatActivity(), HasComponent<LibraryComponent>, FollowersContract.View {

    lateinit var toolbar: Toolbar
    lateinit var stateView: MultiStateView
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: FollowersPresenter

    @Inject
    lateinit var adapter: FollowersAdapter

    val component: FollowersComponent by lazy {
        DaggerFollowersComponent
                .builder()
                .libraryComponent(getComponent())
                .followersModule(FollowersModule(this))
                .build()
    }

    val onLeftFollowerClickListener = object : OnLeftFollowerClickListener {
        override fun onClick(data: LeftFollowerClickData) {
            handleLeftFollowerClick(data)
        }
    }

    val onRightFollowerClickListener = object : OnRightFollowerClickListener {
        override fun onClick(data: RightFollowerClickdata) {
            handleRightFollowerClick(data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followers)

        bindViews()
        inject()
        setupToolbar()
        initializeViews()
        initializeContents()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    override fun getComponent(): LibraryComponent {
        return GithubKotlinApplication.get(this).component
    }

    private fun inject() {
        component.inject(this)
    }

    private fun bindViews() {
        toolbar = findViewById(R.id.toolbar)
        stateView = findViewById(R.id.state_view)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.followers_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeViews() {
        stateView.setAnimateLayoutChanges(true)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter.setLeftFollowerClickListener(onLeftFollowerClickListener)
        adapter.setRightFollowerClickListener(onRightFollowerClickListener)
    }

    private fun initializeContents() {
        val username = intent.getStringExtra(KEY_USERNAME)
        presenter.initialize(username)
    }

    override fun showFollowers(holder: FollowersViewModelHolder) {
        stateView.viewState = MultiStateView.VIEW_STATE_CONTENT

        adapter.setData(holder)
        recyclerView.adapter = adapter
    }

    override fun showFollowersLoading() {
        stateView.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun showFollowersError() {
        stateView.viewState = MultiStateView.VIEW_STATE_ERROR
    }

    private fun handleLeftFollowerClick(data: LeftFollowerClickData) {
        handleFollowerClick(data.url)
    }

    private fun handleRightFollowerClick(data: RightFollowerClickdata) {
        handleFollowerClick(data.url)
    }

    private fun handleFollowerClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    companion object {
        private val KEY_USERNAME = "key_username"

        fun newIntent(context: Context, username: String): Intent {
            val intent = Intent(context, FollowersActivity::class.java)
            intent.putExtra(KEY_USERNAME, username)
            return intent
        }
    }
}