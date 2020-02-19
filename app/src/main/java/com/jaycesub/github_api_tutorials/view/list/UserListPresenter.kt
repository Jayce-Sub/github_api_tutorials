package com.jaycesub.github_api_tutorials.view.list

import android.util.Log
import com.jaycesub.github_api_tutorials.api.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(private val view: UserListContract.View) : UserListContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var _q: String = ""

    var q: String
        get() = _q
        set(value) {
            _q = value
        }

    override fun search() {
        if(q.isBlank()) {
            return
        } // else

        compositeDisposable.add(GithubService.service.getUser(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.showUserList(it)
                }, {
                    Log.w(javaClass.simpleName, it)
                }
            ))
    }

    override fun dropView() {
        compositeDisposable.dispose()
    }
}