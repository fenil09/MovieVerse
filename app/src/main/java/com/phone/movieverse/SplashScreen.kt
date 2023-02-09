package com.phone.movieverse

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val actionbar: ActionBar
        actionbar= supportActionBar!!
        actionbar.setBackgroundDrawable(ColorDrawable(Color.parseColor("#000000")))
        val timer=Timer()
        timer.schedule(4000){
            val intent=Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}