package com.example.assignment.repositories.remote

import com.example.assignment.dashboard.artileModels.MainArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GetDataService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/{page}.json")
    suspend fun getArticlesRequest(
        @Path("page") howBackFar: Int
    ): Response<MainArticleResponse>
}