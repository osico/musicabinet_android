package com.musicabinet.mobile.ui.lessons.lesson.view.sound

import com.musicabinet.mobile.model.lesson.remote.Accompaniment

/**
 * @author Kirchhoff-
 */
class SoundViewPresenter(private val view: SoundViewContract.View) : SoundViewContract.Presenter {

    private lateinit var accompanimentsList: ArrayList<Accompaniment>
    private var currentSelectedPosition = 0

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
    }

    override fun showAccompaniment(position: Int) {
        currentSelectedPosition = position
        view.showAccompaniment(accompanimentsList[currentSelectedPosition])
    }

}