package com.example.assignment.dashboard

import android.content.Context
import androidx.room.Room
import com.example.assignment.dashboard.dashboardRepository.DashboardRepository
import com.example.assignment.dashboard.dashboardViewmodel.DashboardViewmodel
import com.example.assignment.repositories.local.MyDao
import com.example.assignment.repositories.local.MyRoomDatabase
import com.example.assignment.repositories.remote.GetDataService
import com.example.assignment.repositories.remote.RequestInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Constants {
    const val BASEURL = "http://api.nytimes.com/"
    const val API_KEY: String = "6BwaK885XKzLJ68m1kPonqqnDwe9YRbI"


    private fun getRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(RequestInterceptor(API_KEY))
        okHttpClientBuilder.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    fun getService(): GetDataService {
        return getRetrofit().create(GetDataService::class.java)
    }

    private fun getMyArticleDao(context: Context): MyDao {
        val db = Room.inMemoryDatabaseBuilder(context, MyRoomDatabase::class.java)
            .allowMainThreadQueries().build()
        return db.getMyDao()
    }

    private fun getDashboardRepository(context: Context): DashboardRepository {
        return DashboardRepository(getService(), getMyArticleDao(context))
    }

    fun getDashboardViewmodel(context: Context): DashboardViewmodel {
        return DashboardViewmodel(getDashboardRepository(context))
    }
}