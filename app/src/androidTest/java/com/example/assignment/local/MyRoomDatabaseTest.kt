package com.example.assignment.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.repositories.local.MyDao
import com.example.assignment.repositories.local.MyRoomDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MyRoomDatabaseTest : TestCase() {

    private lateinit var db: MyRoomDatabase

    private lateinit var doa: MyDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, MyRoomDatabase::class.java)
            .allowMainThreadQueries().build()
        doa = db.getMyDao()

    }

    /**
     * This test method check both cases like save and get list
     */
    @Test
    fun wirteArticleAndGetArticleList() = runBlocking {
        val list = listOf<Article>()
        list.plus(Article(id = 100032, title = "TestingTitle", section = "Popular Section"))
        list.plus(Article(id = 100033, title = "TestingTitle2", section = "Popular Section"))
        list.plus(Article(id = 100034, title = "TestingTitle3", section = "Popular Section"))
        doa.saveArticles(list)
        assertNotNull(db.getMyDao().getAllArticles())
    }

    @After
    fun closeDb() {
        db.close()
    }

}