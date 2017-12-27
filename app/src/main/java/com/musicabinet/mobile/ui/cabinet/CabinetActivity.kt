package com.musicabinet.mobile.ui.cabinet

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.cabinet.password.CabinetPasswordActivity
import com.musicabinet.mobile.ui.home.HomeActivity
import com.musicabinet.mobile.ui.signup.user.SignUpUserActivity
import com.musicabinet.mobile.ui.slidemenu.SlideMenuActivity
import kotlinx.android.synthetic.main.activity_cabinet.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class CabinetActivity : SlideMenuActivity() {

    companion object {
        const val REQUEST_USER_LOGIN = 4567
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewPager.adapter = CabinetAdapter(this)
        tabLayout.setupWithViewPager(viewPager)

        tvSignIn.setOnClickListener {
            intent = Intent(this, SignUpUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_cabinet, null, false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CabinetPasswordActivity.PASSWORD_REQUEST_CODE) {

            if (resultCode == CabinetPasswordActivity.RESULT_LOGIN) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else if (resultCode == CabinetPasswordActivity.RESULT_ERROR)
                toast("Error")
        } else if (requestCode == REQUEST_USER_LOGIN) {

            if (resultCode == Activity.RESULT_OK) {
                intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}