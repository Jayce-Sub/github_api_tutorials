package com.jaycesub.github_api_tutorials.view.list

import com.jaycesub.github_api_tutorials.BaseContract
import com.jaycesub.github_api_tutorials.model.User

interface UserListContract {

    interface View : BaseContract.View {
        fun showUserList(user: User)
    }

    interface Presenter : BaseContract.Presenter {
        fun search()
    }
}