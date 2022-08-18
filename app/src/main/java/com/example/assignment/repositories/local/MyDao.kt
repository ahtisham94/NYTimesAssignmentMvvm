package com.example.assignment.repositories.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assignment.dashboard.artileModels.Article

@Dao
interface MyDao {

    @Query("SELECT * FROM ARTICLES")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articleEntities: List<Article?>?)

    @Query("SELECT * FROM ARTICLES WHERE id= :id")
    fun getSingleArticle(id: Long): Article

}