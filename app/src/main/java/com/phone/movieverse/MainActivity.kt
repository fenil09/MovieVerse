package com.phone.movieverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var view:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view=findViewById(R.id.imageView)
        getmovies()
    }

    fun getmovies(){
        val movieresponse=movieapi.ombdapi.getmovielist("Iron Man")
        movieresponse.enqueue(object:Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val rescode=response.code()
                Toast.makeText(applicationContext,"response code is $rescode",Toast.LENGTH_LONG).show()
                val data=response.body()
                if(data!=null){
                    val imageurl=data.resultsearch[2].image
                    Glide.with(applicationContext).load(imageurl).into(view)
                }
                else{
                    Toast.makeText(applicationContext,"null response",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                val message=t.message.toString()
                Toast.makeText(applicationContext, message,Toast.LENGTH_LONG).show()
            }

        })
    }
}