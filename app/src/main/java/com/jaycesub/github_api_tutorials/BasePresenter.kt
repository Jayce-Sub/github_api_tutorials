package com.jaycesub.github_api_tutorials

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    protected val compositeDisposable = CompositeDisposable()

    fun dropView() {
        compositeDisposable.dispose()
    }
}