package com.meteoro.githubkotlin.ui.user.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.kennyc.view.MultiStateView
import com.meteoro.githubkotlin.R
import com.meteoro.githubkotlin.application.GithubKotlinApplication
import com.meteoro.githubkotlin.core.di.HasComponent
import com.meteoro.githubkotlin.library.LibraryComponent
import com.meteoro.githubkotlin.ui.followers.presentation.FollowersActivity
import com.meteoro.githubkotlin.ui.user.data.models.GithubUser
import com.meteoro.githubkotlin.ui.user.di.DaggerUserComponent
import com.meteoro.githubkotlin.ui.user.di.UserComponent
import com.meteoro.githubkotlin.ui.user.di.UserModule
import com.squareup.picasso.Picasso
import javax.inject.Inject

/**
 * Created by meteoro on 21/02/2018.
 **/
class UserActivity : AppCompatActivity(), HasComponent<LibraryComponent>, UserContract.View {

    lateinit var toolbar: Toolbar
    lateinit var stateView: MultiStateView
    lateinit var photoAvatar: ImageView
    lateinit var txtLogin: TextView
    lateinit var txtName: TextView
    lateinit var txtLocation: TextView
    lateinit var btnFollowers: Button

    var githubUser: GithubUser? = null

    @Inject
    lateinit var presenter: UserPresenter

    val component: UserComponent by lazy {
        DaggerUserComponent
                .builder()
                .libraryComponent(getComponent())
                .userModule(UserModule(this))
                .build()
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_followers -> openFollowers()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

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
        photoAvatar = findViewById(R.id.photo_avatar)
        txtLogin = findViewById(R.id.login_text)
        txtName = findViewById(R.id.name_text)
        txtLocation = findViewById(R.id.location_text)
        btnFollowers = findViewById(R.id.button_followers)
        btnFollowers.setOnClickListener(clickListener)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.user_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initializeViews() {
        stateView.setAnimateLayoutChanges(true)
    }

    private fun initializeContents() {
        val username = intent.getStringExtra(KEY_USERNAME)
        presenter.initialize(username)
    }

    private fun openFollowers() {
        val username = githubUser?.let {
            val intent = FollowersActivity.newIntent(this, username = it.login)
            startActivity(intent)
        }
    }

    override fun setUser(user: GithubUser) {
        githubUser = user
    }

    override fun showUser() {
        stateView.viewState = MultiStateView.VIEW_STATE_CONTENT
    }

    override fun showUserLoading() {
        stateView.viewState = MultiStateView.VIEW_STATE_LOADING
    }

    override fun showUserError() {
        stateView.viewState = MultiStateView.VIEW_STATE_ERROR
    }

    override fun showPhoto(photoUrl: String) {
        Picasso.with(this)
                .load(photoUrl)
                .into(photoAvatar)
    }

    override fun showLogin(login: String) {
        txtLogin.text = login
    }

    override fun showName(name: String) {
        txtName.text = name
    }

    override fun showLocation(location: String) {
        txtLocation.text = location
    }

    companion object {
        private val KEY_USERNAME = "key_username"

        fun newIntent(context: Context, username: String): Intent {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(KEY_USERNAME, username)
            return intent
        }
    }
}