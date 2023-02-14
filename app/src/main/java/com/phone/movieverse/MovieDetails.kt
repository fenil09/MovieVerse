package com.phone.movieverse

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetails : AppCompatActivity() {
    lateinit var imageview:ImageView
    lateinit var rated:TextView
    lateinit var lottie2:LottieAnimationView
    lateinit var runtime:TextView
    lateinit var imbd:TextView
    lateinit var year:TextView
    lateinit var plot:TextView
    lateinit var director:TextView
    lateinit var rottentomatoes:TextView
    lateinit var metacritic:TextView
    lateinit var storyline:TextView
    lateinit var imbdview:ImageView
    lateinit var rottenview:ImageView
    lateinit var criticview:ImageView
    lateinit var cardview:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val actionbar: ActionBar
        actionbar= supportActionBar!!
        actionbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#7f3e98")))
        imageview=findViewById(R.id.imagedetail)
        rated=findViewById(R.id.textView)
        lottie2=findViewById(R.id.lottie2)
        runtime=findViewById(R.id.textView2)
        imbd=findViewById(R.id.textView4)
        year=findViewById(R.id.textView3)
        plot=findViewById(R.id.textView6)
        director=findViewById(R.id.textView7)
        rottentomatoes=findViewById(R.id.textView10)
        metacritic=findViewById(R.id.textView9)
        storyline=findViewById(R.id.textView5)
        imbdview=findViewById(R.id.imageView)
        rottenview=findViewById(R.id.imageView2)
        criticview=findViewById(R.id.imageView3)
        cardview=findViewById(R.id.card1)
        getmoviedetails()
    }

    fun getmoviedetails(){
        hidecomponents()
        lottie2.isVisible=true
        val id=intent.getStringExtra("movieid").toString()
        val details=movieapi.ombdapi.getmoviedetails(id)
        details.enqueue(object:Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
              lottie2.isVisible=false
                showcomponents()
                val data=response.body()
                if(data!=null){
                    Glide.with(applicationContext).load(data.Poster).into(imageview)
                    rated.setText(data.Rated)
                    runtime.setText(data.Runtime)
                    imbd.setText(data.imbdRating)
                    year.setText(data.Year)
                    plot.setText(data.Plot)
                    director.setText("Director:"+data.Director)
                    rottentomatoes.setText(data.Ratings[1].Value)
                    metacritic.setText(data.Ratings[2].Value)
                }
                else{
                    lottie2.isVisible=false
                    hidecomponents()
                    Toast.makeText(applicationContext,"sorry data is null",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                lottie2.isVisible=false
                hidecomponents()
                Toast.makeText(applicationContext,"Sorry some error occured from Server side",Toast.LENGTH_LONG).show()
            }

        })
    }
    fun hidecomponents(){
        imageview.isVisible=false
       rated.isVisible=false
        runtime.isVisible=false
        imbd.isVisible=false
        year.isVisible=false
        plot.isVisible=false
        director.isVisible=false
        rottentomatoes.isVisible=false
        metacritic.isVisible=false
        storyline.isVisible=false
        imbdview.isVisible=false
        rottenview.isVisible=false
        criticview.isVisible=false
        cardview.isVisible=false
    }
    fun showcomponents(){
        imageview.isVisible=true
        rated.isVisible=true
        runtime.isVisible=true
        imbd.isVisible=true
        year.isVisible=true
        plot.isVisible=true
        director.isVisible=true
        rottentomatoes.isVisible=true
        metacritic.isVisible=true
        storyline.isVisible=true
        imbdview.isVisible=true
        rottenview.isVisible=true
        criticview.isVisible=true
        cardview.isVisible=true
    }
}