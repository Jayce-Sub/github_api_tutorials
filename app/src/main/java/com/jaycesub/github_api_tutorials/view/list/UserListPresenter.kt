package com.jaycesub.github_api_tutorials.view.list

import android.util.Log
import com.jaycesub.github_api_tutorials.BasePresenter
import com.jaycesub.github_api_tutorials.api.GithubService
import com.jaycesub.github_api_tutorials.model.Users
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(private val view: UserListContract.View) : BasePresenter(), UserListContract.Presenter {

    private var _q: String = ""
    private lateinit var userList: Users

    var q: String
        get() = _q
        set(value) {
            _q = value
        }

    override fun search() {
        if(q.isBlank()) {
            return
        } // else

        compositeDisposable.add(GithubService.service.getUserList(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userList = it
                    view.showUserList(userList)
                }, {
                    Log.w(javaClass.simpleName, it)
                }
            ))
    }

    override fun requestPreview() {
        if(q.isBlank()) {
            return
        } // else

        compositeDisposable.add(GithubService.service.getUserList(q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    userList = it
                    view.showPreview(userList)
                }, {
                    Log.w(javaClass.simpleName, it)
                }
            ))

    }

    override fun requestUserInfo(position: Int) {
        if(!::userList.isInitialized) {
            return
        }

        if(position >= userList.items.size) {
            return
        }

        view.showUserInfo(userList.items[position].login)
    }

}