package com.musicabinet.mobile.ui.lessons.lesson.dialog.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.Lesson
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class LessonSelectAdapter(items: List<Lesson>)
    : BaseRecyclerAdapter<LessonSelectViewHolder, Lesson>(items) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LessonSelectViewHolder(parent.inflate(R.layout.item_lesson_select))


    override fun onBindViewHolder(holder: LessonSelectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position))
    }
}