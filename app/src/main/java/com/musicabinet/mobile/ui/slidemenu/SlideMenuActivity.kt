package com.musicabinet.mobile.ui.slidemenu

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.home.HomeActivity
import com.musicabinet.mobile.ui.instrument.InstrumentActivity
import kotlinx.android.synthetic.main.activity_slide_menu.*
import kotlinx.android.synthetic.main.view_slide_menu.*

/**
 * @author Kirchhoff-
 */
abstract class SlideMenuActivity : AppCompatActivity(), SlideMenuContract.View {

    private lateinit var presenter: SlideMenuContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)

        contentLayout.addView(inflateLayout())

        presenter = SlideMenuPresenter(this)

        educationLayout.setOnClickListener { presenter.onEducationClick() }
        homeLayout.setOnClickListener { presenter.onHomeClick() }
    }

    protected abstract fun inflateLayout(): View

    override fun moveToEducation() {
        val intent = Intent(this, InstrumentActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun moveToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun logout() {
    }

    override fun moveToMyAccount() {
    }

}