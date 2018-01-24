package com.musicabinet.mobile.ui.lessons.lesson.tonechord.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import kotlinx.android.synthetic.main.item_tone_chord.view.*

/**
 * @author Kirchhoff-
 */
class ToneAndChordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: ToneOrChord) {
        with(element) {
            itemView.tvToneChord.text = name
        }
    }
}