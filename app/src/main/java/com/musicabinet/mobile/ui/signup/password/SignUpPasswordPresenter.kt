package com.musicabinet.mobile.ui.signup.password

import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class SignUpPasswordPresenter(private val view: SignUpPasswordContract.View,
                              private val repository: MusicabinetRepository)
    : SignUpPasswordContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun onUserType(password: String, confirmPassword: String) {
        view.enableNextButton(!password.isEmpty() && !confirmPassword.isEmpty())
    }

    override fun registerUser(email: String, name: String, surname: String,
                              password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            view.showPasswordMismatchError()
            return
        }

        subscriptions.add(repository.registerUser(email, password, name, surname)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showLoading(true) }
                .doOnTerminate { view.showLoading(false) }
                .subscribe({ view.moveToFinishRegistration() }, { view.showError() }))
    }

}