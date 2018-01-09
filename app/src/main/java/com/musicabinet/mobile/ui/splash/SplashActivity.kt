package com.musicabinet.mobile.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.home.HomeActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val presenter = SplashPresenter(Injection.provideRepository(), this,
            Injection.provideStorage())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun moveToHome() {
        Observable.just(true)
                .delay(2, TimeUnit.SECONDS)
                .subscribe {
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
    }
}
