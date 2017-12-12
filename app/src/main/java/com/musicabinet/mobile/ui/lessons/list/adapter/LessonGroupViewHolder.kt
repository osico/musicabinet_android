package com.musicabinet.mobile.ui.lessons.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
import kotlinx.android.synthetic.main.item_lesson_group.view.*

/**
 * @author Kirchhoff-
 */
class LessonGroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(group: InstrumentGroup) {
        itemView.container.removeAllViews();
        val lessonTitleView = LessonTitleView(itemView.context)
        lessonTitleView.bind(group.module)

        itemView.container.addView(lessonTitleView)

        for (item in group.lessonList) {
            val lessonView = LessonItemView(itemView.context)
            lessonView.bind(item)
            itemView.container.addView(lessonView)
        }
    }
}