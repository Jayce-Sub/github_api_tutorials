package com.jaycesub.github_api_tutorials

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.jaycesub.github_api_tutorials.view.user.UserActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserTest {

    val id = "Jayce-Sub"

    @Rule
    @JvmField
    val userActivity = ActivityTestRule(UserActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun showUserTest() {

        val intent = Intent()
        intent.putExtra(Constants.INTENT_LOGIN, id)
        userActivity.launchActivity(intent)

        Thread.sleep(5000)

        onView(withId(R.id.textView_id))
            .check(matches(withText(containsString(id))))
    }

    @After
    fun finished() {
        Intents.release()
    }
}