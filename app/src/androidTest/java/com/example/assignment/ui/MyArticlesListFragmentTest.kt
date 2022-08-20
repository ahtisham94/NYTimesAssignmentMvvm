package com.example.assignment.ui

import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.assignment.R
import com.example.assignment.dashboard.Constants
import com.example.assignment.dashboard.DashboardActivity
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MyArticlesListFragmentTest {
    @get : Rule
    val activityRule = ActivityScenarioRule(DashboardActivity::class.java)
    private lateinit var dashboardViewmodel: DashboardViewmodel
    private lateinit var navController: NavHostController

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        dashboardViewmodel =
            Constants.getDashboardViewmodel(ApplicationProvider.getApplicationContext())
        //Getting the NavController for test
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.dashboard_nav)
        }
    }

    /**
     * This test method perform scenario open app
     * Check Recycler is visible
     */

    @Test
    fun articleRecyclerviewTest() {
        onView(withId(R.id.articleRv)).check(matches(isDisplayed()))
    }


}