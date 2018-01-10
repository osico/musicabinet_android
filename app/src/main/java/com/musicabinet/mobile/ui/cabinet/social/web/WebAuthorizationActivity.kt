package com.musicabinet.mobile.ui.cabinet.social.web

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_web.*
import org.jetbrains.anko.toast

/**
 * @author Kirchhoff-
 */
class WebAuthorizationActivity : AppCompatActivity(), WebAuthorizationContract.View {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        webView.settings.javaScriptEnabled = true
        webView.clearCache(true)
        webView.webViewClient = MusicabinetWebClient(presenter)
        webView.loadUrl(intent.getStringExtra(URL_ARG))
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }


    override fun showLoading(show: Boolean) {
        loadingDialog?.dismiss()

        if (show) {
            loadingDialog = LoadingDialog()
            loadingDialog?.show(supportFragmentManager, "TAG")
        }
    }

    override fun showError() {
        toast("Error")
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