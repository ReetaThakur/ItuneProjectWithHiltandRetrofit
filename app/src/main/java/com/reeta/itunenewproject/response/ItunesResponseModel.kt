package com.reeta.itunenewproject.response

import com.google.gson.annotations.SerializedName

data class ItunesResponseModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val resultModels: List<ResultModel>
)