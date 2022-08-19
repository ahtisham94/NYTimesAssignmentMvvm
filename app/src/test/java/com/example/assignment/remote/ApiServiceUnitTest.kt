package com.example.assignment.remote

import com.example.assignment.dashboard.artileModels.MainArticleResponse
import com.example.assignment.repositories.remote.RequestInterceptor
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@RunWith(JUnit4::class)
class ApiServiceUnitTest {
    var apiService: ApiService? = null
    val mockArticleSuccessResponse = "article_success_res.json"
    val mockArticleFailureResponse = "article_failure_res.json"

    @Before
    fun createService() {

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
        apiService = retrofit.create(ApiService::class.java)
    }


    /**
     * The method check if API working fine
     */
    @Test
    fun checkApiWorkingTest() {
        try {
            val response = apiService?.getArticlesRequest(7)?.execute()
            assertEquals(response?.code(), 200)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * The test method check if API response body is same as expected
     */
    @Test
    fun checkApiResponseBodyTest() {
        try {
            val response = apiService?.getArticlesRequest(7)?.execute()
            val reader = MockResponseFileReader(mockArticleSuccessResponse)
            val testingSuccessResponse =
                Gson().fromJson(reader.content, MainArticleResponse::class.java)
            assertEquals(testingSuccessResponse.status, response?.body()?.status)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun checkApiErrorCodeTest() {
        try {
            val response = apiService?.getArticlesRequest(17)?.execute()
            assertEquals(response?.code(), 404)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Test
    fun readArticleSuccessResponseTest() {
        val reader = MockResponseFileReader(mockArticleSuccessResponse)
        assertNotNull(reader.content)
    }

    @Test
    fun readArticleFailureResponseTest() {
        val reader = MockResponseFileReader(mockArticleFailureResponse)
        assertNotNull(reader.content)
    }
}