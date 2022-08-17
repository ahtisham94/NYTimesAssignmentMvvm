package com.example.assignment.coreBase

import com.google.gson.annotations.SerializedName


data class BaseResponseModel<Any>(
    @SerializedName("success", alternate = ["Success"]) val success: Boolean,
    @SerializedName("message", alternate = ["Message"]) val message: String,
    @SerializedName("code", alternate = ["Code"]) val code: String,
    @SerializedName("data", alternate = ["Data"]) val data: Any?,
) {


}