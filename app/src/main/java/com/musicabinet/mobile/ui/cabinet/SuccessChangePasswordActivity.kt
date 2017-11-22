package com.musicabinet.mobile.ui.cabinet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R

/**
 * @author Kirchhoff-
 */
class SuccessChangePasswordActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_change_password)
    }


    override fun onBackPressed() {
        intent = Intent(this, CabinetActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}