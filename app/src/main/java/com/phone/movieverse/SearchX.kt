package com.phone.movieverse

import com.google.gson.annotations.SerializedName

data class SearchX(
    @SerializedName("Title")
    val title:String,
    @SerializedName("Year")
    val year:String,
    @SerializedName("Poster")
    val image:String
)
