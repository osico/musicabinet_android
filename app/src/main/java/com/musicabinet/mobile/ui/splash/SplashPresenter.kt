package com.musicabinet.mobile.ui.splash

import com.musicabinet.mobile.api.ApiFactory
import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class SplashPresenter(private val repository: MusicabinetRepository,
                      private val view: SplashContract.View,
                      private val storage: KeyValueStorage) : SplashContract.Presenter {

    private val subscriptions = CompositeDisposable()


    override fun subscribe() {
        if (storage.isUserExist()) {
            subscriptions.add(repository.getUserProfile()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ profile: UserProfile ->
                        storage.saveUserInformation(profile)

                        view.moveToHome()
                    }, {
                        storage.clear()
                        ApiFactory.clearCookie()
                    }))
        } else {
            view.moveToHome()
        }
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}