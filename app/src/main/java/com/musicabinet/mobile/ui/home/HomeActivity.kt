package com.musicabinet.mobile.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author Kirchhoff-
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewPager.adapter = HomeAdapter(this)
        tabLayout.setupWithViewPager(viewPager)
    }
}