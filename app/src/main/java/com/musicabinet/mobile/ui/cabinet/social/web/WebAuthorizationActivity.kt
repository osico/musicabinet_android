package com.musicabinet.mobile.ui.cabinet.social.web

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.musicabinet.mobile.R
import kotlinx.android.synthetic.main.activity_web_authorization.*

/**
 * @author Kirchhoff-
 */
class WebAuthorizationActivity : AppCompatActivity(), WebAuthorizationContract.View {

    companion object {
        const val URL_ARG = "URL_ARG"

        fun startWebAuthorization(context: Context, url: String) {
            val intent = Intent(context, WebAuthorizationActivity::class.java)
            intent.putExtra(URL_ARG, url)
            context.startActivity(intent)
        }
    }

    val presenter = WebAuthorizationPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_authorization)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView.settings.javaScriptEnabled = true
        webView.clearCache(true)
        webView.webViewClient = MusicabinetWebClient(presenter)
        webView.loadUrl(intent.getStringExtra(URL_ARG))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }


    class MusicabinetWebClient(val presenter: WebAuthorizationPresenter) : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            if (presenter.shouldLoadUrl(url))
                super.onPageStarted(view, url, favicon)
            else
                view?.stopLoading()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }
}