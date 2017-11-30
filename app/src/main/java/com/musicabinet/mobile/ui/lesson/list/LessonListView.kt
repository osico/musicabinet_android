package com.musicabinet.mobile.ui.lesson.list

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.musicabinet.mobile.R
import com.musicabinet.mobile.model.instrument.matrix.local.InstrumentGroup
import com.musicabinet.mobile.ui.lesson.list.adapter.LessonAdapter
import kotlinx.android.synthetic.main.view_lesson_list.view.*

/**
 * @author Kirchhoff-
 */
class LessonListView : FrameLayout {

    private lateinit var lessonAdapter: LessonAdapter

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_lesson_list, this, true)
        lessonRecyclerView.layoutManager = LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL, false)
    }

    public fun setLessonList(list: List<InstrumentGroup>) {
        lessonAdapter = LessonAdapter(list)
        lessonRecyclerView.adapter = lessonAdapter
    }
}