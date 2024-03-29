package com.musicabinet.mobile.ui.instrument

import com.musicabinet.mobile.model.instrument.InstrumentDataElement
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.repository.keyvalue.KeyValueStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*

/**
 * @author Kirchhoff-
 */
class InstrumentPresenter(private val repository: MusicabinetRepository,
                          private val storage: KeyValueStorage,
                          private val view: InstrumentContract.View) : InstrumentContract.Presenter {

    private val subscriptions = CompositeDisposable()

    override fun loadInstrumentList() {

        subscriptions.add(repository.getInstrumentList()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.showLoading(true)
                    view.showError(false)
                    view.showInstrumentList(false)
                    view.showPageIndicator(false)
                }
                .doOnTerminate {
                    view.showLoading(false)
                }
                .map { instrumentData ->
                    Collections.sort(instrumentData.instrumentList)
                    instrumentData.instrumentList
                }
                .subscribe({ instrumentList: List<InstrumentDataElement>? ->
                    if (instrumentList == null || instrumentList.isEmpty())
                        view.showError(true)
                    else {
                        view.setInstrumentList(instrumentList)
                        view.showInstrumentList(true)
                        view.showPageIndicator(true)
                    }
                }, { view.showError(true) }))
    }

    override fun onInstrumentSelected(instrument: InstrumentDataElement) {
        storage.saveSelectedInstrumentId(instrument.id)
        view.moveToCourseActivity(instrument.nameLocalized, instrument.id)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}