package com.example.assignment.di

import android.content.Context
import androidx.room.Room
import com.example.assignment.R
import com.example.assignment.repositories.local.MyRoomDatabase
import com.example.assignment.repositories.remote.GetDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Singleton
    @Provides
    fun getRetrofit(@BaseUrlQualifier baseUrl: String): Retrofit {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(interceptor)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()

    }

    @Singleton
    @Provides
    fun getDataService(retrofit: Retrofit) = retrofit.create(GetDataService::class.java)

    @Singleton
    @Provides
    @BaseUrlQualifier
    fun getBaseUrl(@ApplicationContext context: Context) = context.getString(R.string.base_url)

    @Singleton
    @Provides
    @APIKeyQualifier
    fun getApikey(@ApplicationContext context: Context) = context.getString(R.string.api_key)

    @Singleton
    @Provides
    fun getRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MyRoomDatabase::class.java, "MyArticleDB"
    ).allowMainThreadQueries().build()

    @Singleton
    @Provides
    fun getDao(myArticleDb: MyRoomDatabase) = myArticleDb.getMyDao()
}