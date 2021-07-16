package com.android.mytask.model

data class NewsResponse(
    val copyright: String,
    val num_results: Int,
    val results: ArrayList<Result>,
    val status: String
)