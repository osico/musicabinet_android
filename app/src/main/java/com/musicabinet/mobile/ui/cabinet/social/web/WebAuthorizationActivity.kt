package com.musicabinet.mobile.ui.cabinet.social.web

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.toast
import com.musicabinet.mobile.ui.ActionBarActivity
import com.musicabinet.mobile.ui.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_web.*
/**
 * @author Kirchhoff-
 */
class WebAuthorizationActivity : ActionBarActivity(), WebAuthorizationContract.View {

    companion object {
        const val URL_ARG = "URL_ARG"

        fun startWebAuthorization(activity: Activity, requestCode: Int, url: String) {
            val intent = Intent(activity, WebAuthorizationActivity::class.java)
            intent.putExtra(URL_ARG, url)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    val presenter = WebAuthorizationPresenter(this, Injection.provideRepository(),
            Injection.provideStorage())

    private var loadingDialog: LoadingDialog? = null

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        webView.settings.javaScriptEnabled = true
        webView.clearCache(true)
        webView.webViewClient = MusicabinetWebClient(presenter)
        webView.loadUrl(intent.getStringExtra(URL_ARG))
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun showLoading(show: Boolean) {
        loadingDialog?.dismiss()

        if (show) {
            loadingDialog = LoadingDialog()
            loadingDialog?.show(supportFragmentManager, "TAG")
        }
    }

    override fun showError() {
        toast(R.string.internal_error)
    }

    override fun moveToHomeScreen() {
        setResult(Activity.RESULT_OK)
        finish()
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