package com.musicabinet.mobile.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.slidemenu.SlideMenuActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * @author Kirchhoff-
 */
class HomeActivity : SlideMenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager.adapter = HomeAdapter(this)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_home, null, false)
    }

}