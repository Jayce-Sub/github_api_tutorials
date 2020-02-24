package com.jaycesub.github_api_tutorials

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jaycesub.github_api_tutorials.view.list.UserListActivity
import com.jaycesub.github_api_tutorials.view.user.UserActivity
import org.hamcrest.core.StringContains.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListTest {

    @Rule
    @JvmField
    val userListActivity = ActivityTestRule(UserListActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun insertUserNameTest() {
        val text = "jjs"
        userListActivity.launchActivity(Intent())
        onView(withId(R.id.editText_search))
            .perform(typeText(text), closeSoftKeyboard())
        onView(withId(R.id.button_search))
            .perform(click())
        // TODO : Change using IdlingResource
        Thread.sleep(5000)
        onView(withId(R.id.textView_totalCount))
            .check(matches(withText(containsString("total count"))))
    }

    @Test
    fun showUserActivityTest() {
        insertUserNameTest()
        onView(withId(R.id.recyclerView_userList))
            .perform(click())
        intended(hasComponent(UserActivity::class.java.name))
    }

    @After
    fun finished() {
        Intents.release()
    }
}