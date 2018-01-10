package com.musicabinet.mobile.ui.signup

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.ActivityWithBackButton
import com.musicabinet.mobile.ui.cabinet.CabinetActivity
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

/**
 * @author Kirchhoff-
 */
class SignUpFinishActivity : ActivityWithBackButton() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bFinish.setOnClickListener {
            moveToCabinet()
        }
    }

    @SuppressLint("InflateParams")
    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_sign_up_finish, null, false)
    }

    override fun showCompanyIcon() = true

    override fun onBackPressed() {
        moveToCabinet()
    }

    private fun moveToCabinet() {
        intent = Intent(this, CabinetActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}