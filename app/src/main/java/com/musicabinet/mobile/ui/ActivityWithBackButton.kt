package com.musicabinet.mobile.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import kotlinx.android.synthetic.main.activity_with_back_button.*

/**
 * @author Kirchhoff-
 */
abstract class ActivityWithBackButton : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_back_button)

        contentLayout.addView(inflateLayout())

        ivCompany.setVisible(showCompanyIcon())

        ivBack.setOnClickListener { onBackPressed() }
    }

    protected abstract fun inflateLayout(): View

    protected abstract fun showCompanyIcon(): Boolean
}