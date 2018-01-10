package com.musicabinet.mobile.ui.web

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.ActionBarActivity
import kotlinx.android.synthetic.main.activity_web.*

/**
 * @author Kirchhoff-
 */
class WebVideoActivity : ActionBarActivity() {

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

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(intent.getStringExtra(WebVideoActivity.URL_ARG))

        title = intent.getStringExtra(WebVideoActivity.TITLE_ARG)
    }

}