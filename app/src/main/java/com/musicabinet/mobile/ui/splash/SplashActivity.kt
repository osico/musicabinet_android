package com.musicabinet.mobile.ui.splash

import android.content.Intent
import android.os.Bundle
import com.musicabinet.archmvp.BaseActivity
import com.musicabinet.mobile.Injection
import com.musicabinet.mobile.R
import com.musicabinet.mobile.ui.home.HomeActivity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity<SplashContract.View, SplashContract.Presenter>(), SplashContract.View {

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

    override fun initPresenter(): SplashContract.Presenter {
        return SplashPresenter(Injection.provideRepository(), Injection.provideStorage())
    }
}
