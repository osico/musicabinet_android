package com.musicabinet.mobile.ui.slidemenu

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.Gravity
import android.view.View
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.extensions.toast
import com.musicabinet.mobile.ui.cabinet.CabinetActivity
import com.musicabinet.mobile.ui.home.HomeActivity
import com.musicabinet.mobile.ui.instrument.InstrumentActivity
import kotlinx.android.synthetic.main.activity_slide_menu.*
import kotlinx.android.synthetic.main.view_slide_header.*
import kotlinx.android.synthetic.main.view_slide_menu.*
/**
 * @author Kirchhoff-
 */
abstract class SlideMenuActivity : AppCompatActivity(), SlideMenuContract.View {

    companion object {
        const val OPEN_SCREEN_DELAY = 200L
    }

    private lateinit var presenter: SlideMenuContract.Presenter
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_menu)

        contentLayout.addView(inflateLayout())

        presenter = SlideMenuPresenter(this, Injection.provideStorage())

        educationLayout.setOnClickListener { presenter.onEducationClick() }
        homeLayout.setOnClickListener { presenter.onHomeClick() }
        accountLayout.setOnClickListener { presenter.onMyAccountClick() }
        logOutLayout.setOnClickListener { presenter.onLogOutClick() }

        ivDrawer.setOnClickListener { drawer.openDrawer(Gravity.START, true) }

        val arrowDrawable = DrawerArrowDrawable(this)
        arrowDrawable.color = ContextCompat.getColor(this, R.color.white)
        ivDrawer.setImageDrawable(arrowDrawable)

        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {
            }

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                arrowDrawable.progress = slideOffset
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.configMenuVisibility()
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
        drawer.closeDrawers()

        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, OPEN_SCREEN_DELAY)

        toast(R.string.log_out_success)
    }

    override fun moveToMyAccount() {
        drawer.closeDrawers()

        if (this !is CabinetActivity) {
            handler.postDelayed({
                val intent = Intent(this, CabinetActivity::class.java)
                startActivity(intent)
                finish()
            }, OPEN_SCREEN_DELAY)
        }
    }

    override fun showLoginUserMenu(userName: String, email: String) {
        logOutLayout.setVisible(true)
        accountLayout.setVisible(false)
        musicabinetElement.setVisible(false)
        userElement.setVisible(true)
        tvUserName.text = userName
        tvEmail.text = email
    }

    override fun showNotLoginUserMenu() {
        logOutLayout.setVisible(false)
        accountLayout.setVisible(true)
        musicabinetElement.setVisible(true)
        userElement.setVisible(false)
    }

}