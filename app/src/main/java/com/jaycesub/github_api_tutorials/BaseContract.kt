package com.jaycesub.github_api_tutorials

interface BaseContract {

    interface View {

    }

    interface Presenter {
        fun dropView()
    }
}