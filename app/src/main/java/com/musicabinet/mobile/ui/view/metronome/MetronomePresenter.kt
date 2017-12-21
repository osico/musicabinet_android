package com.musicabinet.mobile.ui.view.metronome

/**
 * @author Kirchhoff-
 */
class MetronomePresenter(private val view: MetronomeContract.View) : MetronomeContract.Presenter {

    private var period = 0L
    private var playing = false

    override fun subscribe() {
        view.showPeriod(period.toString())
        view.showStartActionButton()
    }

    override fun addPeriod() {
        if (period < 300)
            period += 15

        view.showPeriod(period.toString())

        if (playing)
            view.startTick(period)
    }

    override fun subPeriod() {
        if (period > 0)
            period -= 15

        view.showPeriod(period.toString())

        if (playing)
            view.startTick(period)
    }

    override fun actionClick() {
        playing = !playing

        if (playing) {
            view.showStopActionButton()
            view.startTick(period)
        } else {
            view.showStartActionButton()
            view.stopTick()
        }
    }
}