package com.musicabinet.mobile.ui.lessons.lesson.dialog.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.musicabinet.mobile.extensions.setVisible
import com.musicabinet.mobile.model.lesson.Lesson
import kotlinx.android.synthetic.main.item_lesson_select.view.*

/**
 * @author Kirchhoff-
 */
class LessonSelectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(element: Lesson, isSelected: Boolean) {
        with(element) {
            itemView.tvLesson.text = name
            itemView.ivSelected.setVisible(isSelected)
        }
    }

}