package com.jaycesub.github_api_tutorials.view.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.jaycesub.github_api_tutorials.Constants.INTENT_LOGIN
import com.jaycesub.github_api_tutorials.R
import com.jaycesub.github_api_tutorials.model.User
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserContract.View {

    private lateinit var presenter: UserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        presenter = UserPresenter(this)

        initIntentData()
    }

    private fun initIntentData() {
        presenter.userName = intent.getStringExtra(INTENT_LOGIN)
    }

    override fun onResume() {
        super.onResume()
        presenter.requestUserInfo()
    }

    override fun showUserInfo(user: User) {
        runOnUiThread {
            // Login Id
            textView_id.text = user.login

            // Bio
            textView_bio.text = user.bio

            // Avatar
            Glide.with(this)
                .load(user.avatar_url)
                .into(imageView_avatar)
        }

    }
}