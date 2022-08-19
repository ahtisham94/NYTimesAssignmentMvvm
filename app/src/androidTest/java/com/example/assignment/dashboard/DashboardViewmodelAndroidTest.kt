package com.example.assignment.dashboard

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.assignment.dashboard.dashboardRepository.DashboardRepository
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import com.example.assignment.repositories.local.MyDao
import com.example.assignment.repositories.local.MyRoomDatabase
import com.example.assignment.repositories.remote.RequestInterceptor
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class DashboardViewmodelAndroidTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    lateinit var dataService: com.example.assignment.repositories.remote.GetDataService
    lateinit var dao: MyDao

    val dispatcher = StandardTestDispatcher()
    lateinit var dashboardViewmodel: DashboardViewmodel
    lateinit var dashboardRepository: DashboardRepository

    @Before
    fun setUp() {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(RequestInterceptor(Constants.API_KEY))
        okHttpClientBuilder.addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
        dataService =
            retrofit.create(com.example.assignment.repositories.remote.GetDataService::class.java)
        val context = ApplicationProvider.getApplicationContext<Context>()
        val db = Room.inMemoryDatabaseBuilder(context, MyRoomDatabase::class.java)
            .allowMainThreadQueries().build()
        dao = db.getMyDao()
        Dispatchers.setMain(dispatcher)
        dashboardRepository = DashboardRepository(dataService, dao)
        dashboardViewmodel = DashboardViewmodel(dashboardRepository)
    }

    @Test
    fun getArticlesTest() {
        dashboardViewmodel.getArticles()
        assertTrue(dashboardViewmodel.apiChannel.isEmpty)
        assertNotNull(dashboardViewmodel.getArticleList())
    }



}