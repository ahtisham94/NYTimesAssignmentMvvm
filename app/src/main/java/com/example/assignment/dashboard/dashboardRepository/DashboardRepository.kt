package com.example.assignment.dashboard.dashboardRepository

import com.example.assignment.coreBase.BaseRepository
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.repositories.local.MyDao
import com.example.assignment.repositories.remote.GetDataService
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val apiService: GetDataService,
    private val myDao: MyDao

) :
    BaseRepository() {

    /**
     * This method get articles list from API
     */
    suspend fun getArticles(howBackFar: Int, apiKey: String) = baseApiResultHandler() {
        apiService.getArticlesRequest(howBackFar, apiKey)
    }


    /**
     * This method get updated list from room database
     */
     fun getArticlesLocally() = myDao.getAllArticles()

    /**
     * This method save list in database
     */
     fun saveArticles(list: List<Article>) = myDao.saveArticles(list)
}
