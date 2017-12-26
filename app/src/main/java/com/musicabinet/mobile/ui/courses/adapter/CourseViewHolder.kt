package com.musicabinet.mobile.ui.courses.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.extensions.setCompletedPercent
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentCourse
import kotlinx.android.synthetic.main.item_course.view.*

/**
 * @author Kirchhoff-
 */
class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: InstrumentCourse) {
        with(element) {
            itemView.tvName.text = name
            itemView.tvPercent.setCompletedPercent(percent)
        }
    }
}