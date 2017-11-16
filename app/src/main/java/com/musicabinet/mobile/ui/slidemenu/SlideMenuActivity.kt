package com.musicabinet.mobile.ui.slidemenu

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.activity_slide_menu.*

/**
 * @author Kirchhoff-
 */
abstract class SlideMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)

        contentLayout.addView(inflateLayout())
    }


    protected abstract fun inflateLayout(): View
}