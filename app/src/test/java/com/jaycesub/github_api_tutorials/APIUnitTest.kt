package com.jaycesub.github_api_tutorials

import com.jaycesub.github_api_tutorials.api.GithubService
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.util.concurrent.CountDownLatch

class APIUnitTest {
    @Test
    fun getUserListTest() {

        val countDown = CountDownLatch(1)

        val q = "jjs"
        GithubService.service.getUserList(q)
            .subscribeOn(Schedulers.io())
            .subscribe {
                countDown.countDown()
            }

        countDown.await()
    }

    @Test
    fun getUser() {
        val countDown = CountDownLatch(1)

        val userName = "jayce-sub"
        GithubService.service.getUserInfo(userName)
            .subscribeOn(Schedulers.io())
            .subscribe {
                countDown.countDown()
            }

        countDown.await()
    }
}