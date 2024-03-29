package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import com.musicabinet.mobile.model.lesson.remote.Stave
import com.musicabinet.mobile.repository.MusicabinetRepository
import com.musicabinet.mobile.utils.FileUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import okio.Okio
import java.io.File
import java.io.IOException
import java.util.*

/**
 * @author Kirchhoff-
 */
class GuideMachinePresenter(private val view: GuideMachineContract.View,
                            private val repository: MusicabinetRepository,
                            private val internalDirectory: File) : GuideMachineContract.Presenter {

    private val subscriptions = CompositeDisposable()

    private var row = 0
    private var firstSelect = true

    private var improvisationFileId: String? = null

    override fun subscribe(stave: Stave?, userStaveId: Boolean) {
        if (stave == null) {
            view.addRow(row)
            view.showLoading(false)
        } else {
            view.showLoading(true)
            downloadImprovisationFile(stave)

            if (userStaveId)
                improvisationFileId = stave.file.id
            else
                improvisationFileId = "";
        }
    }

    override fun onElementSelected(rowString: String) {

        if (firstSelect) {
            firstSelect = false
            row += 1
            view.addRow(row)
            return
        }

        val rowInt = rowString.toInt()
        if (rowInt == row) {
            row++
            view.addRow(row)
        }
    }

    override fun addedRows(rowValue: Int) {
        firstSelect = false

        //Added rows , +1 for empty row in the end
        while (rowValue + 1 >= row) {
            view.addRow(row)
            row += 1
        }
    }

    override fun getImprovisationFileId() = improvisationFileId


    private fun downloadImprovisationFile(stave: Stave) {
        subscriptions.add(repository.downloadFileWithUUID(stave.file.id)
                .flatMap { responseBodyResponse ->
                    object : Observable<File>() {
                        override fun subscribeActual(observer: Observer<in File>) {
                            try {
                                val fileName = stave.name
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
                .doOnTerminate { view.showLoading(false) }
                .subscribe({ file: File ->
                    val list = FileUtils.getDataFromFile(file.path)
                    Collections.sort(list)
                    view.showImprovisationNote(list)
                }, { t: Throwable ->
                    view.showError()
                }))
    }

}