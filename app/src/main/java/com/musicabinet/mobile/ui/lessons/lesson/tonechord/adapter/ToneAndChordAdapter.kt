package com.musicabinet.mobile.ui.lessons.lesson.tonechord.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.machine.ToneOrChord
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class ToneAndChordAdapter(items: List<ToneOrChord>)
    : BaseRecyclerAdapter<ToneAndChordViewHolder, ToneOrChord>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ToneAndChordViewHolder(parent.inflate(R.layout.item_tone_chord))

    override fun onBindViewHolder(holder: ToneAndChordViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}