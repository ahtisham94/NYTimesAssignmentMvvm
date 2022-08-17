package com.example.assignment.dashboard.artileModels

import com.google.gson.annotations.SerializedName


data class MainArticleResponse(
    @SerializedName("status") val status: String,
    @SerializedName("num_results") val numResults: String,
    @SerializedName("results") val results : ArrayList<Article>,
)