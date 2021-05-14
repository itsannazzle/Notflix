package com.example.notflix.ui.home

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.notflix.R
import com.example.notflix.utils.DataMovies
import com.example.notflix.utils.IdlingResource
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.TestCase
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
        onView(withId(R.id.rv_eps)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun displayFavoriteMovieScreenTest(){
        onView(withId(R.id.favoriteFragment)).perform(click())
        onView(withId(R.id.rv_fav_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()
        ))

    }

    @Test
    fun displayFavoriteTvScreenTest(){
        onView(withId(R.id.bottom_nav)).check(matches(isDisplayed()))
        onView(withId(R.id.bottom_nav)).perform(click())
        onView(withId(R.id.rv_fav_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_fav_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size))
    }

    @After
    fun teardown(){
        IdlingRegistry.getInstance().unregister(IdlingResource.getEspressoIdlingResource())

    }
}

