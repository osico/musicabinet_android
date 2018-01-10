package com.musicabinet.mobile.ui.cabinet.password

import com.musicabinet.mobile.model.profile.UserProfile
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class CabinetPasswordPresenter(private val repository: MusicabinetRepository,
                               private val storage: KeyValueStorage,
                               private val view: CabinetPasswordContract.View)
    : CabinetPasswordContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun onUserType(password: String) {
        view.enableNextButton(!password.isEmpty())
    }

    override fun forgotPassword() {
        view.moveToForgotPasswordScreen()
    }

    override fun loginUser(email: String, password: String) {

        subscriptions.add(repository.login(email, password)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .subscribe({
                    getUserProfile()
                }, { t: Throwable? ->
                    view.showEmailError()
                }))

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
                    view.showEmailError()
                }))
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}