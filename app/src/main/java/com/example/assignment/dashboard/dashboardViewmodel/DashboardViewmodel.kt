package com.example.assignment.dashboard.dashboardViewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.coreBase.APIState
import com.example.assignment.coreBase.ApiCode
import com.example.assignment.coreBase.BaseViewmodel
import com.example.assignment.dashboard.artileModels.Article
import com.example.assignment.dashboard.artileModels.MainArticleResponse
import com.example.assignment.dashboard.dashboardObservers.ArticleListObserver
import com.example.assignment.dashboard.dashboardObservers.MyArticleDetailObserver
import com.example.assignment.dashboard.dashboardRepository.DashboardRepository
import com.example.assignment.di.APIKeyQualifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewmodel @Inject constructor() : BaseViewmodel() {

    @Inject
    lateinit var dashboardRepository: DashboardRepository

    val articleObserver = ArticleListObserver()
    val articleDetailsObserver = MyArticleDetailObserver()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        viewModelScope.launch {
            apiChannel.send(APIState.Error(exception.message ?: "Unknown exception"))
        }
    }


    /**
     * This method save list in database and get list from database if internet is not available
     */
    fun getArticles() {
        viewModelScope.launch(exceptionHandler) {

            dashboardRepository.getArticles(7)
                .collect {
                    when (it) {
                        is APIState.NetworkResponseSuccess -> {
                            Log.i("res", it.response.toString())
                            val response = it.response as MainArticleResponse
                            dashboardRepository.saveArticles(response.results)
                        }
                        is APIState.Error -> {
                            if (it.errorMessage.contains(ApiCode.NO_INTERNET.codeOrDesc, true)) {
                                dashboardRepository.getArticlesLocally()
                            } else {
                                apiChannel.send(it)
                            }

                        }
                        is APIState.ShowHideDialog -> {
                            apiChannel.send(it)
                        }
                        else -> {
                        }
                    }
                }
        }
    }

    /**
     * This method get updated list from local database and update UI
     */
    fun getArticleList(): LiveData<List<Article>> {
        return dashboardRepository.getArticlesLocally()
    }
}