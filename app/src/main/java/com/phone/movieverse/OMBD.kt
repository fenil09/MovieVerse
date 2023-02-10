package com.phone.movieverse

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDB {
    @GET("?&apikey=fd2afd8d")
    fun getmovielist(@Query("s") s:String):Call<Search>

    @GET("?&apikey=fd2afd8d")
    fun getmoviedetails(@Query("i") id:String):Call<Details>
}

object movieapi{
    val ombdapi:OMDB
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(values.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ombdapi=retrofit.create(OMDB::class.java)
    }

}