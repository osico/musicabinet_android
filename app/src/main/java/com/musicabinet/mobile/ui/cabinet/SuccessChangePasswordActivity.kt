package com.musicabinet.mobile.ui.cabinet

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.ActivityWithBackButton

/**
 * @author Kirchhoff-
 */
class SuccessChangePasswordActivity : ActivityWithBackButton() {

    @SuppressLint("InflateParams")
    override fun inflateLayout(): View {
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        return inflater.inflate(R.layout.activity_success_change_password, null, false)
    }

    override fun showCompanyIcon() = true

    override fun onBackPressed() {
        intent = Intent(this, CabinetActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}