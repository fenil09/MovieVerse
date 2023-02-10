package com.phone.movieverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val id=intent.getStringExtra("movieid").toString()
        Toast.makeText(this,"movie id is $id",Toast.LENGTH_LONG).show()
        getmoviedetails()
    }

    fun getmoviedetails(){
        val id=intent.getStringExtra("movieid").toString()
        val details=movieapi.ombdapi.getmoviedetails(id)
        details.enqueue(object:Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                val code=response.code()
                Toast.makeText(applicationContext,"Status code is $code",Toast.LENGTH_LONG).show()
                val data=response.body()
                if(data!=null){
                    Log.d("moviedetail",data.toString())
                }
                else{
                    Toast.makeText(applicationContext,"sorry data is null",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Toast.makeText(applicationContext,"Sorry some error occured from Server side",Toast.LENGTH_LONG).show()
            }

        })
    }
}