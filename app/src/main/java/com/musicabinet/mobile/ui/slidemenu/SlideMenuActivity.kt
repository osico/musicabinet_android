package com.musicabinet.mobile.ui.slidemenu

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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

    private val OPEN_SCREEN_DELAY = 200L

    private lateinit var presenter: SlideMenuContract.Presenter
    private val handler = Handler()

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
        drawer.closeDrawers()

        if (this !is InstrumentActivity) {
            handler.postDelayed({
                val intent = Intent(this, InstrumentActivity::class.java)
                startActivity(intent)
                finish()
            }, OPEN_SCREEN_DELAY)
        }
    }

    override fun moveToHome() {
        drawer.closeDrawers()

        if (this !is HomeActivity) {
            handler.postDelayed({
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }, OPEN_SCREEN_DELAY)
        }
    }

    override fun logout() {
    }

    override fun moveToMyAccount() {
    }

}