package com.victoriasecret.movie

import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.victoriasecret.movie.views.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.After
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class LoginTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get: Rule
    val loginActivityRule = ActivityTestRule(
        LoginActivity::class.java,
        false, false
    )

    @Before
    fun setup() {
        Intents.init()

        val startIntent = Intent(context, LoginActivity::class.java)
        //this is the key part
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //this is the key part
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        loginActivityRule.launchActivity(startIntent)

    }

    @Test
    fun testLogin() {
        SystemClock.sleep(3000)
        Espresso.onView(withId(R.id.btnLogin))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.etUserName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.etUserName)).perform(replaceText("Ironman"))

        Espresso.onView(withId(R.id.etPassword))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.etPassword)).perform(replaceText("Ironman@123"))

        Espresso.onView(withId(R.id.btnLogin))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.btnLogin))
            .perform(ViewActions.click())

    }

    @After
    fun clear() {
        Intents.release()
    }
}