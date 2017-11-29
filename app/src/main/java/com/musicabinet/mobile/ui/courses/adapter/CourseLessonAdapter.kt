package com.musicabinet.mobile.ui.courses.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class CourseLessonAdapter(items: List<InstrumentCourse>)
    : BaseRecyclerAdapter<CourseLessonViewHolder, InstrumentCourse>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CourseLessonViewHolder(parent.inflate(R.layout.item_lesson))

    override fun onBindViewHolder(holder: CourseLessonViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}