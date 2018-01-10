package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import com.musicabinet.mobile.model.lesson.remote.Accompaniment
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import okio.Okio
import java.io.File
import java.io.IOException
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author Kirchhoff-
 */
class SoundViewPresenter(private val view: SoundViewContract.View,
                         private val repository: MusicabinetRepository,
                         private val internalDirectory: File) : SoundViewContract.Presenter {

    private lateinit var accompanimentsList: ArrayList<Accompaniment>
    private var currentSelectedPosition = 0
    private lateinit var fileCounter: AtomicInteger
    private lateinit var musicListId: ArrayList<String>
    private var isPlaying = false

    private val subscriptions = CompositeDisposable()

    override fun setAccompanimentsData(accompaniments: Set<Accompaniment>) {
        accompanimentsList = ArrayList(accompaniments)

        currentSelectedPosition = 0
        if (!accompaniments.isEmpty()) {
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

            checkFileAvailable()
        } else {
            view.setElementVisibility(false)
        }
    }

    override fun showAccompaniment(position: Int) {
        view.stopPlay()
        isPlaying = false
        currentSelectedPosition = position
        view.showAccompaniment(accompanimentsList[currentSelectedPosition])

        checkFileAvailable()
    }

    override fun play() {
        if (!isPlaying)
            view.startPlay()
        else
            view.stopPlay()

        isPlaying = isPlaying.not()
    }

    override fun stop() {
        isPlaying = false
    }

    override fun getSoundsId() = musicListId

    private fun checkFileAvailable() {
        val accompaniment = accompanimentsList[currentSelectedPosition]
        musicListId = ArrayList()

        //Check is data is available
        if (accompaniment.bass != null && accompaniment.bass.dataAvailable)
            musicListId.add(accompaniment.bass.id)

        if (accompaniment.drums != null && accompaniment.drums.dataAvailable)
            musicListId.add(accompaniment.drums.id)

        if (accompaniment.keys != null && accompaniment.keys.dataAvailable)
            musicListId.add(accompaniment.keys.id)


        //Check data on disk
        if (!musicListId.isEmpty()) {
            val downloadedFileId = ArrayList<String>()

            for (id in musicListId)
                if (!isFileExist(id))
                    downloadedFileId.add(id)

            if (!downloadedFileId.isEmpty())
                downloadAccompaniment(downloadedFileId)
            else {
                view.setAudioFiles(musicListId)
            }

        }
    }

    private fun downloadAccompaniment(list: List<String>) {

        fileCounter = AtomicInteger(list.size)

        for (item in list) {
            subscriptions.add(repository.downloadFile(item)
                    .flatMap { responseBodyResponse ->
                        object : Observable<File>() {
                            override fun subscribeActual(observer: Observer<in File>) {
                                try {
                                    val fileName = item
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
                    }.observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val result = fileCounter.decrementAndGet()
                        if (result <= 0) {
                            view.showLoading(false)
                            view.setAudioFiles(musicListId)
                        } else
                            view.showLoading(true)
                    }, { t: Throwable ->
                        val result = fileCounter.decrementAndGet()
                        if (result <= 0)
                            view.showLoading(false)
                        else
                            view.showLoading(true)
                    }))
        }
    }

    private fun isFileExist(id: String): Boolean {
        val file = File(internalDirectory, id)
        return file.exists()
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }
}