package com.musicabinet.mobile.ui.lessons.list.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class LessonAdapter(items: List<InstrumentGroup>)
    : BaseRecyclerAdapter<LessonGroupViewHolder, InstrumentGroup>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LessonGroupViewHolder(parent.inflate(R.layout.item_lesson_group))

    override fun onBindViewHolder(holder: LessonGroupViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}