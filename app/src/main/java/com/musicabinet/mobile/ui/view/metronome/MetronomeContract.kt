package com.musicabinet.mobile.ui.view.metronome

/**
 * @author Kirchhoff-
 */
interface MetronomeContract {

    interface View {

        fun showPeriod(period: String)

        fun showStartActionButton()

        fun showStopActionButton()

        fun startTick(period: Long)

        fun stopTick()
    }

    interface Presenter {

        fun subscribe()

        fun addPeriod()

        fun subPeriod()

        fun actionClick()

    }
}