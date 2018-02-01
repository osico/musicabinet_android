package com.musicabinet.mobile.ui.lessons.lesson.view.guide.machine

import com.musicabinet.mobile.model.lesson.remote.Stave
import com.musicabinet.mobile.repository.MusicabinetRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Kirchhoff-
 */
class GuideMachinePresenter(private val view: GuideMachineContract.View,
                            private val repository: MusicabinetRepository) : GuideMachineContract.Presenter {

    private val subscriptions = CompositeDisposable()

    private var row = 0
    private var firstSelect = true

    override fun subscribe(stave: Stave?) {
        if (stave == null)
            view.addRow(row)
        else {
            view.showLoading(true)
            downloadImprovisationFile(stave)
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


    private fun downloadImprovisationFile(stave: Stave) {

    }
}