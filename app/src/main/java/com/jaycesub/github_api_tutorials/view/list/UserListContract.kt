package com.jaycesub.github_api_tutorials.view.list

import com.jaycesub.github_api_tutorials.BaseContract
import com.jaycesub.github_api_tutorials.model.Users

interface UserListContract {

    interface View : BaseContract.View {
        fun showUserList(users: Users)
        fun showUserInfo(login: String)
    }

    interface Presenter : BaseContract.Presenter {
        fun search()
        fun requestUserInfo(position: Int)
    }
}