package com.ridhoafni.codeinmoviedb.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ridhoafni.codeinmoviedb.MainActivity
import com.ridhoafni.codeinmoviedb.R

class SplashScreenActivity : AppCompatActivity() {
    private var delay: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val imageView = findViewById<ImageView>(R.id.image_loading)
        Glide.with(baseContext).load(R.raw.marvel).into(imageView)
        Handler(mainLooper).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }
}