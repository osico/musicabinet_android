package com.musicabinet.mobile.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}
