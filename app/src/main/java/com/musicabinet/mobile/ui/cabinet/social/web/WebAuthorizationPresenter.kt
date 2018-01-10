package com.musicabinet.mobile.ui.cabinet.social.web

import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class WebAuthorizationPresenter(private val view: WebAuthorizationContract.View,
                                private val repository: MusicabinetRepository,
                                private val storage: KeyValueStorage)
    : WebAuthorizationContract.Presenter {

    private val subscriptions = CompositeDisposable()

    companion object {
        const val MUSICABINET_ENDPOINT = "https://app.musicabinet.com"
    }

    override fun shouldLoadUrl(url: String?): Boolean {
        if (url != null && url.startsWith(MUSICABINET_ENDPOINT)) {
            getUserProfile()
            return false
        }

        return true
    }

    private fun getUserProfile() {
        subscriptions.add(repository.getUserProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .subscribe({ profile: UserProfile ->
                    storage.saveUserInformation(profile)
                    view.moveToHomeScreen()
                }, { t: Throwable? ->
                    view.showError()
                }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}