package com.jaycesub.github_api_tutorials.view.user

import android.util.Log
import com.jaycesub.github_api_tutorials.BasePresenter
import com.jaycesub.github_api_tutorials.api.GithubService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserPresenter(private val view: UserContract.View) : BasePresenter(), UserContract.Presenter {

    private var _userName: String? = null

    var userName: String?
        get() = _userName
        set(value) {
            _userName = value
        }

    override fun requestUserInfo() {
        if(userName == null) {
            // TODO : Error
            return
        }

        compositeDisposable.add(GithubService.service.getUserInfo(userName!!)
            .subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.showUserInfo(it)
                }, {
                    Log.w(javaClass.simpleName, it)
                }
            ))
    }
}