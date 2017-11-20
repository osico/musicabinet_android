package com.musicabinet.mobile.ui.signup

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.cabinet.CabinetActivity
import kotlinx.android.synthetic.main.activity_sign_up_finish.*

/**
 * @author Kirchhoff-
 */
class SignUpFinishActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_finish)

        bFinish.setOnClickListener {
            moveToCabinet()
        }
    }

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