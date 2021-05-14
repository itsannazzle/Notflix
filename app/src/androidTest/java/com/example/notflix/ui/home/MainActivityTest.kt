package com.example.notflix.ui.home

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notflix.R
import com.example.notflix.utils.DataMovies
import com.example.notflix.utils.IdlingResource
import com.google.android.material.tabs.TabLayout
import junit.framework.TestCase
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest : TestCase(){
    private val dummyMovies = DataMovies.generateDataMovies()
    private val dummyTvShow = DataMovies.generateDataTvShow()
    private lateinit var instrumental : Context

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup(){
        instrumental = InstrumentationRegistry.getInstrumentation().targetContext
        IdlingRegistry.getInstance().register(IdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun displayMoviesListTest(){
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun displayMoviesDetailTest(){

        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()
        ))
        onView(withId(R.id.movies_title)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_country)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_rating)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_duration)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_desc)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_genre)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_poster)).check(matches(isDisplayed()))


        onView(withId(R.id.movie_rec)).check(matches(isDisplayed()))
        onView(withId(R.id.movie_rec)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun displayTvShowListTest(){
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @Test
    fun displayTvShowDetailTest(){
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()
        ))
        onView(withId(R.id.movies_title)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_country)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_rating)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_duration)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_desc)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_genre)).check(matches(isDisplayed()))

        onView(withId(R.id.movies_poster)).check(matches(isDisplayed()))


        onView(withId(R.id.rv_eps)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_eps)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

    @Test
    fun displayFavoriteMovieScreenTest(){
        onView(withId(R.id.favoriteFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()
        ))
    }


    @Test
    fun displayFavoriteTvScreenTest(){
        onView(withId(R.id.favoriteFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
        onView(withId(R.id.rv_fav_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()
        ))
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(IdlingResource.getEspressoIdlingResource())

    }
}

