package com.musicabinet.mobile.ui.view.metronome

/**
 * @author Kirchhoff-
 */
class MetronomePresenter(private val view: MetronomeContract.View) : MetronomeContract.Presenter {

    private var period = 0
    private var playing = false

    override fun subscribe() {
        view.showPeriod(period.toString())
        view.showStartActionButton()
    }

    override fun addPeriod() {
        if (period < 300)
            period += 15

        view.showPeriod(period.toString())
    }

    override fun subPeriod() {
        if (period > 0)
            period -= 15

        view.showPeriod(period.toString())
    }

    override fun actionClick() {
        playing = !playing

        if (playing)
            view.showStopActionButton()
        else
            view.showStartActionButton()
    }
}