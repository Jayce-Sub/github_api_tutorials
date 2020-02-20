package com.jaycesub.github_api_tutorials.view.user

import com.jaycesub.github_api_tutorials.BaseContract
import com.jaycesub.github_api_tutorials.model.User

interface UserContract {

    interface View: BaseContract.View {
        fun showUserInfo(user: User)
    }

    interface Presenter: BaseContract.Presenter {
        fun requestUserInfo()
    }
}