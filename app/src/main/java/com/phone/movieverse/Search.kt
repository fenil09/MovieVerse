package com.phone.movieverse

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("Response")
    val response:String,
    @SerializedName("Search")
    val resultsearch:List<SearchX>,
    @SerializedName("totalResults")
    val total:String
)
