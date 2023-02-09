package com.phone.movieverse

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Adapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerview:RecyclerView
    lateinit var lottie:LottieAnimationView
    lateinit var moviename:EditText
    lateinit var search:ImageButton
    lateinit var clear:ImageButton
    lateinit var imageview:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lottie=findViewById(R.id.lottie1)
        moviename=findViewById(R.id.et1)
        search=findViewById(R.id.imageButton2)
        clear=findViewById(R.id.imageButton)
        imageview=findViewById(R.id.imageView5)
        recyclerview=findViewById(R.id.recyclerView)
        imageview.isVisible=true
        recyclerview.isVisible=false
        val actionbar:ActionBar
        actionbar= supportActionBar!!
        actionbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#7f3e98")))
        search.setOnClickListener {
            val searchdata=moviename.text.toString()
            getmovies(searchdata)
        }
        clear.setOnClickListener {
            recyclerview.isVisible= false
            imageview.isVisible=true
            moviename.setText("")
        }

    }

    fun getmovies(searchmovie:String){
        lottie.isVisible=true
        imageview.isVisible=false
        val movieresponse=movieapi.ombdapi.getmovielist(searchmovie)
        movieresponse.enqueue(object:Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                lottie.isVisible=false
                recyclerview.isVisible=true
                val data=response.body()
                if(data!=null){
                    val adapter=movieadapter(this@MainActivity,data.resultsearch)
                    recyclerview.adapter=adapter
                    recyclerview.layoutManager=GridLayoutManager(this@MainActivity,2)
                }
                else{
                    recyclerview.isVisible=false
                    lottie.isVisible=false
                    imageview.isVisible=true
                    Toast.makeText(applicationContext,"null response",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                recyclerview.isVisible=false
                lottie.isVisible=false
                imageview.isVisible=true
                val message=t.message.toString()
                Toast.makeText(applicationContext, message,Toast.LENGTH_LONG).show()
            }

        })
    }
}