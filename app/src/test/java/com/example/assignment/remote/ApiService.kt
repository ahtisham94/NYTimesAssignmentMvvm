package com.example.assignment.remote

import com.example.assignment.dashboard.artileModels.MainArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("svc/mostpopular/v2/mostviewed/all-sections/{page}.json")
    fun getArticlesRequest(
        @Path("page") howBackFar: Int
    ): Call<MainArticleResponse>
}