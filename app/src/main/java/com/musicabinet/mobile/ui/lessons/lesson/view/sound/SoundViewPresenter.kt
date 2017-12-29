package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import okio.Okio
import java.io.File
import java.io.IOException

/**
 * @author Kirchhoff-
 */
class SoundViewPresenter(private val view: SoundViewContract.View,
                         private val repository: MusicabinetRepository,
                         private val internalDirectory: File) : SoundViewContract.Presenter {

    private lateinit var accompanimentsList: ArrayList<Accompaniment>
    private var currentSelectedPosition = 0

    private val subscriptions = CompositeDisposable()

    override fun setAccompanimentsData(accompaniments: Set<Accompaniment>) {
        accompanimentsList = ArrayList(accompaniments)

        currentSelectedPosition = 0
        view.setAccompanimentList(accompanimentsList)
        view.showAccompaniment(accompanimentsList[currentSelectedPosition])

        var shouldShowElement = false
        for (accompaniment in accompanimentsList) {
            if (accompaniment.keys != null && accompaniment.keys.dataAvailable) {
                shouldShowElement = true
                break
            }

            if (accompaniment.drums != null && accompaniment.drums.dataAvailable) {
                shouldShowElement = true
                break
            }

            if (accompaniment.bass != null && accompaniment.bass.dataAvailable) {
                shouldShowElement = true
                break
            }
        }

        view.setElementVisibility(shouldShowElement)

        downloadAccompaniment()
    }

    override fun showAccompaniment(position: Int) {
        currentSelectedPosition = position
        view.showAccompaniment(accompanimentsList[currentSelectedPosition])
    }


    private fun downloadAccompaniment() {
        val fileId = accompanimentsList[currentSelectedPosition].bass?.id
        subscriptions.add(repository.downloadFile(fileId!!).flatMap { responseBodyResponse ->
            object : Observable<File>() {
                override fun subscribeActual(observer: Observer<in File>) {
                    try {
                        val fileName = fileId
                        // will create file in global Music directory, can be any other directory, just don't forget to handle permissions
                        val file = File(internalDirectory, fileName)

                        val sink = Okio.buffer(Okio.sink(file))
                        // you can access body of response
                        sink.writeAll(responseBodyResponse.body()!!.source())
                        sink.close()
                        observer.onNext(file)
                        observer.onComplete()
                    } catch (e: IOException) {
                        e.printStackTrace()
                        observer.onError(e)
                    }

                }
            }
        }.subscribe({}, {}))
    }
}