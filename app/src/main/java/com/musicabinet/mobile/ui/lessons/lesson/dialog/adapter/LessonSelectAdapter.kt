package com.musicabinet.mobile.ui.lessons.lesson.dialog.adapter

import android.view.ViewGroup
import com.musicabinet.mobile.R
import com.musicabinet.mobile.extensions.inflate
import com.musicabinet.mobile.model.lesson.lesson.Lesson
import com.musicabinet.mobile.utils.BaseRecyclerAdapter

/**
 * @author Kirchhoff-
 */
class LessonSelectAdapter
    : BaseRecyclerAdapter<LessonSelectViewHolder, Lesson>, BaseRecyclerAdapter.OnItemClickWithPositionListener<Lesson> {

    private var selectedList: MutableList<Boolean>
    private var selectedPosition: Int

    constructor(items: List<Lesson>) : super(items) {
        selectedList = MutableList(items.size, { false })
        selectedPosition = 0
        selectedList[selectedPosition] = true
        setOnItemClickWithPosition(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            LessonSelectViewHolder(parent.inflate(R.layout.item_lesson_select))


    override fun onBindViewHolder(holder: LessonSelectViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(getItem(position), selectedList[position])
    }

    override fun onItemClick(item: Lesson, position: Int) {
        if (!selectedList[position]) {
            selectedList = MutableList(getDataSet().size, { false })
            selectedList[position] = true
            notifyDataSetChanged()
        }
    }

    public fun getSelectedItem() = getItem(selectedPosition)
}