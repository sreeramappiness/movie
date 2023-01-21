package com.victoriasecret.movie

import android.content.Intent
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.victoriasecret.movie.views.LoginActivity
import com.victoriasecret.movie.views.PopularMovieActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PopularAndDetailListTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get: Rule
    val popularActivityRule = ActivityTestRule(
        PopularMovieActivity::class.java,
        false, false
    )

    @Before
    fun setup() {
        Intents.init()

        val startIntent = Intent(context, PopularMovieActivity::class.java)
        //this is the key part
        startIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        //this is the key part
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        popularActivityRule.launchActivity(startIntent)

    }

    @Test
    fun testPopularDetails() {
        SystemClock.sleep(9000)
        Espresso.onView(withId(R.id.rvItemPopular))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    RecyclerViewItemClick.clickChildViewWithId(R.id.itemPopular)
                )
            )
    }

    @Test
    fun testSearchFun(){
        val searchText = "Wed"
        SystemClock.sleep(5000)
        Espresso.onView(withId(R.id.rvItemPopular))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        SystemClock.sleep(1000)
        Espresso.onView(withId(R.id.searchBar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.searchBar)).perform(ViewActions.click())
        SystemClock.sleep(1000)
        Espresso.onView(withId(R.id.searchBar)).perform(typeText("Wed"), closeSoftKeyboard())
    }

    @After
    fun clear() {
        Intents.release()
    }

}