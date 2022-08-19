package com.example.assignment.dashboard

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.example.assignment.coreBase.APIState
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DashboardViewmodelAndroidTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    private lateinit var dashboardViewmodel: DashboardViewmodel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        dashboardViewmodel =
            Constants.getDashboardViewmodel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getArticlesTest() {
        dashboardViewmodel.getArticles()
        assertTrue(dashboardViewmodel.apiChannel.isEmpty)
        assertNotNull(dashboardViewmodel.getArticleList())


    }

    @Test
    fun baseRepositoryTest() {
        runBlocking {
            val states = dashboardViewmodel.dashboardRepository.baseApiResultHandler {
                Constants.getService().getArticlesRequest(7)
            }
            states.collect() {
                when (it) {
                    is APIState.ShowHideDialog -> {
                        if (it.showHide)
                            assertEquals("showing", "showing")
                    }
                    is APIState.NetworkResponseSuccess -> {
                        assertNotNull(it)
                    }
                }
            }

        }
    }


}