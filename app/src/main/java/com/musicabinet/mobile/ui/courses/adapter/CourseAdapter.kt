package com.musicabinet.mobile.ui.courses.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class CourseAdapter(items: List<InstrumentCourse>)
    : BaseRecyclerAdapter<CourseViewHolder, InstrumentCourse>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CourseViewHolder(parent.inflate(R.layout.item_course))

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }

}