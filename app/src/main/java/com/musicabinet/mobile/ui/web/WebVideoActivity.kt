package com.musicabinet.mobile.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.activity_web.*

/**
 * @author Kirchhoff-
 */
class WebVideoActivity : AppCompatActivity() {

    companion object {

        const val URL_ARG = "URL_ARG"
        const val TITLE_ARG = "TITLE_ARG"

        fun startWebVideo(context: Context, url: String, title: String) {
            val intent = Intent(context, WebVideoActivity::class.java)
            intent.putExtra(URL_ARG, url)
            intent.putExtra(TITLE_ARG, title)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(intent.getStringExtra(WebVideoActivity.URL_ARG))

        title = intent.getStringExtra(WebVideoActivity.TITLE_ARG)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }

}